package com.bi16java;

import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by tsytrin on 03.01.2017.
 */
public class Main {
    // Maximum number of accounts to generate
    static final int NUM_ACCOUNTS = 1000;
    // Maximum number of credit
    static final int MAX_CREDIT_CARDS_PER_ACCOUINT = 7;
    static final int MAX_TRANSACTIONS_PER_CARD = 20;
    static final BigDecimal MAX_AMOUNT = new BigDecimal(10000 + ".00");


    static Faker faker = new Faker(new Locale("en"));

    static List<Integer> generateAccounts() {
        List<Integer> generatedRowsIDs = new ArrayList<>();
        AccountDAOImpl accountDAO = new AccountDAOImpl();
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            com.github.javafaker.Name fakeName = faker.name();
            Account account = new Account(0, fakeName.firstName(), fakeName.lastName(),faker.address().countryCode());
            generatedRowsIDs.add(accountDAO.insert(account));
        }
        return generatedRowsIDs;
    }

    public static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
    }


    static List<Integer> generateCards(List<Integer> accountsIDs){
        List<Integer> generatedCards = new ArrayList<>();
        CreditCardDAOImpl cardDAO = new CreditCardDAOImpl();

        for (int accountID:accountsIDs) {
            int numCards = ThreadLocalRandom.current().nextInt(0, MAX_CREDIT_CARDS_PER_ACCOUINT + 1);

            for (int i = 0; i < numCards; i++) {
                BigDecimal actualRandomAmount = generateRandomBigDecimalFromRange(new BigDecimal(0), MAX_AMOUNT);
                CreditCard creditCard = new CreditCard(0, actualRandomAmount,accountID);
                generatedCards.add(cardDAO.insert(creditCard));
            }
        }
        return generatedCards;
    }

    static List<Integer> generateTransactions(List<Integer> generatedCards){
        List<Integer> transactions = new ArrayList<>();
        CreditCardDAOImpl cardDAO = new CreditCardDAOImpl();
        TransactionDAOImpl transactionDAODAO = new TransactionDAOImpl();

        for (int cardID:generatedCards) {
            CreditCard card = cardDAO.get(cardID);
            BigDecimal maxCardTransactionAmount = card.getCashAmount();
            int numTransaction = ThreadLocalRandom.current().nextInt(0, MAX_TRANSACTIONS_PER_CARD + 1);
            for (int i = 0; i < numTransaction; i++) {
                BigDecimal transactionCandidate = generateRandomBigDecimalFromRange(new BigDecimal(0), maxCardTransactionAmount);
                int recipientCandidate = generatedCards.get(ThreadLocalRandom.current().nextInt(0, generatedCards.size()));
                if (recipientCandidate != cardID) {
                    Transaction transaction = new Transaction(0, transactionCandidate, cardID, recipientCandidate);
                    transactions.add(transactionDAODAO.insert(transaction));
                    maxCardTransactionAmount = maxCardTransactionAmount.subtract(transactionCandidate);
                    if (maxCardTransactionAmount.compareTo(new BigDecimal(0)) < 1)
                        i = numTransaction;
                }
            }
        }
        return generatedCards;
    }


    public static void main(String[] args) {
        List<Integer> accountsIDs = generateAccounts();
        List<Integer> cardsIDs = generateCards(accountsIDs);
        generateTransactions(cardsIDs);
    }
}


/*
DELETE FROM `bi16-java`.Transaction;
DELETE FROM `bi16-java`.CreditCard;
DELETE FROM `bi16-java`.Account;

 */