package com.bi16java;

/**
 * Created by tsytrin on 03.01.2017.
 */
public class Account {

    private int accountID;
    private String firstName;
    private String lastName;
    private String country;

    Account(int accountID, String firstName, String lastName, String country) {
        this.accountID = accountID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    int getAccountID(){return accountID;}
    String getFirstName(){return firstName;}
    String getLastName(){return lastName;}
    String getCountry(){return country;}


}
