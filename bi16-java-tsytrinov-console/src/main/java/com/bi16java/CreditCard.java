package com.bi16java;

import java.math.BigDecimal;

/**
 * Created by tsytrin on 03.01.2017.
 */
public class CreditCard {

    private int creditCardID;
    private BigDecimal cashAmount;
    private int accountID;

    CreditCard(int creditCardID, BigDecimal cashAmount, int accountID){
        this.accountID = accountID;
        this.creditCardID = creditCardID;
        this.cashAmount = cashAmount;
    }

    int getCreditCardID(){return creditCardID;}
    BigDecimal getCashAmount(){return cashAmount;}
    int getAccountID(){return accountID;}

}
