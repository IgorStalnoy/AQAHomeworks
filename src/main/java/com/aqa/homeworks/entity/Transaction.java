package com.aqa.homeworks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private int id;
    private int accountId;
    private double amount;

    public Transaction(int accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public Transaction(double amount) {
        this.amount = amount;
    }
}
