package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.db.Dao;
import com.aqa.homeworks.entity.Account;

import java.util.List;

public interface AccountDao extends Dao<Account> {

    List<Account> getAllByUserId(int userId);

    Account getByID(int accountId);
}
