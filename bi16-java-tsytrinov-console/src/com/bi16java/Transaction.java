package com.bi16java;

import java.math.BigDecimal;

/**
 * Created by tsytrin on 03.01.2017.
 */
public class Transaction {

    private int transactionID;
    private BigDecimal amount;
    private int fromCreditCardID;
    private int toCreditCardID;

    Transaction(int transactionID, BigDecimal amount, int fromCreditCardID, int toCreditCardID){
        this.transactionID = transactionID;
        this.amount = amount;
        this.fromCreditCardID = fromCreditCardID;
        this.toCreditCardID = toCreditCardID;
    }


    int getTransactionID(){
        return transactionID;
    }

    BigDecimal getAmount(){
        return amount;
    }

    int getFromCreditCardID(){
        return fromCreditCardID;
    }

    int getToCreditCardID(){
        return toCreditCardID;
    }

}
