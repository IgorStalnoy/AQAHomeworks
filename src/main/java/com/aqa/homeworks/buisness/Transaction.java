package com.aqa.homeworks.buisness;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private int id;
    private int accountId;
    private double amount;

    public Transaction(int accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}
