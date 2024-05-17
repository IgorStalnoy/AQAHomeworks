package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.db.Dao;
import com.aqa.homeworks.entity.Transaction;

public interface TransactionDao extends Dao<Transaction> {
    void createTopUpTransaction(Transaction transaction);

    void createWithDrawTransaction(Transaction transaction);
}
