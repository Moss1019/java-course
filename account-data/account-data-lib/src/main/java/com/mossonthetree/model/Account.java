package com.mossonthetree.model;

public class Account {
    public String accountId;

    public String userName;

    public double balance;

    public Account() {

    }

    public Account(String accountId, String userName, double balance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
    }
}
