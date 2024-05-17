package com.aqa.homeworks.entity;

import lombok.Getter;

@Getter
public class Account {
    private int id;
    private int userId;
    private double balance;
    private final String currency;

    public Account(int id, double balance, String currency) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(int userId, String currency) {
        this.userId = userId;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "accountID : " + id + " | balance : " + balance + " | currency : " + currency;
    }

}
