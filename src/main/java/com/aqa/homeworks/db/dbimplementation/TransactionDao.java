package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.buisness.Transaction;
import com.aqa.homeworks.db.Dao;

public interface TransactionDao extends Dao<Transaction> {
        void createTopUpTransaction(Transaction transaction);
        void createWithDrawTransaction(Transaction transaction);
}
