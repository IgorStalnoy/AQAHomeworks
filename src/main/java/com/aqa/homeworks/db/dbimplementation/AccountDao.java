package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.buisness.Account;
import com.aqa.homeworks.db.Dao;

import java.util.List;

public interface AccountDao extends Dao<Account> {

    List<Account> getAccountListByUserId(int userId);
    Account getAccountByAccountID(int accountId, int userID);
    double getAccountBalance(int accountID);
}
