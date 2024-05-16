package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.buisness.Account;
import com.aqa.homeworks.utils.QueriesPropertiesManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.aqa.homeworks.db.DBConstants.DB_CONNECTION;

public class AccountDaoImpl implements AccountDao {
    Properties queries = QueriesPropertiesManager.getProperties();

    @Override
    public void create(Account account) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement createAccount = connection.prepareStatement(queries.getProperty("createAccount"))) {
            createAccount.setInt(1, account.getUserId());
            createAccount.setString(2, account.getCurrency());
            createAccount.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAll() {
        List<Account> accountList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAllAccounts = connection.prepareStatement(queries.getProperty("getAllAccounts"))) {
            ResultSet resultSet = getAllAccounts.executeQuery();
            while (resultSet.next()) {
                int accountID = resultSet.getInt("accountID");
                int balance = resultSet.getInt("balance");
                String currency = resultSet.getString("currency");
                accountList.add(new Account(accountID, balance, currency));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    @Override
    public List<Account> getAccountListByUserId(int userId) {
        List<Account> accountsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAccountList = connection.prepareStatement(queries.getProperty("getAllUsersAccounts"))) {
            getAccountList.setInt(1, userId);
            ResultSet resultSet = getAccountList.executeQuery();
            while (resultSet.next()) {
                int accountId = resultSet.getInt("accountID");
                double balance = resultSet.getDouble("balance");
                String currency = resultSet.getString("currency");
                accountsList.add(new Account(accountId, balance, currency));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountsList;
    }
    @Override
    public Account getAccountByAccountID(int accountId, int userId) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAccount = connection.prepareStatement(queries.getProperty("getAccountByUserId"))) {
            getAccount.setInt(1, userId);
            getAccount.setInt(2, accountId);
            ResultSet resultSet = getAccount.executeQuery();
            if (resultSet.next()) {
                int accountIdResult = resultSet.getInt("accountID");
                double balanceResult = resultSet.getDouble("balance");
                String currencyResult = resultSet.getString("currency");
                return new Account(accountIdResult, balanceResult, currencyResult);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getAccountBalance(int accountID) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAccountBalance = connection.prepareStatement(queries.getProperty("getAccountBalance"))) {
            getAccountBalance.setInt(1, accountID);
            ResultSet resultSet = getAccountBalance.executeQuery();
            return resultSet.getDouble("balance");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}