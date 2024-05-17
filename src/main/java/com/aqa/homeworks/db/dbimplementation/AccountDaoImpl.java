package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.entity.Account;
import com.aqa.homeworks.utils.PropertiesManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.aqa.homeworks.db.DBConstants.DB_CONNECTION;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void create(Account account) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement createAccount = connection.prepareStatement(PropertiesManager.getQueryProperty("createAccount"))) {
            createAccount.setInt(1, account.getUserId());
            createAccount.setString(2, account.getCurrency());
            createAccount.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAll() {
        List<Account> accountList;
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAllAccounts = connection.prepareStatement(PropertiesManager.getQueryProperty("getAllAccounts"))) {
            ResultSet resultSet = getAllAccounts.executeQuery();
            accountList = addAllResultsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    @Override
    public List<Account> getAllByUserId(int userId) {
        List<Account> accountsList;
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAccountList = connection.prepareStatement(PropertiesManager.getQueryProperty("getAllUsersAccounts"))) {
            getAccountList.setInt(1, userId);
            ResultSet resultSet = getAccountList.executeQuery();
            accountsList = addAllResultsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountsList;
    }

    @Override
    public Account getByID(int accountId) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAccount = connection.prepareStatement(PropertiesManager.getQueryProperty("getAccountByUserId"))) {
            getAccount.setInt(1, accountId);
            ResultSet resultSet = getAccount.executeQuery();
            if (resultSet.next()) {
                return getAccountFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Account> addAllResultsToList(ResultSet resultSet) throws SQLException {
        List<Account> accountList = new ArrayList<>();
        while (resultSet.next()) {
            accountList.add(getAccountFromResultSet(resultSet));
        }
        return accountList;
    }

    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        int accountId = resultSet.getInt("accountID");
        double balance = resultSet.getDouble("balance");
        String currency = resultSet.getString("currency");
        return new Account(accountId, balance, currency);
    }
}