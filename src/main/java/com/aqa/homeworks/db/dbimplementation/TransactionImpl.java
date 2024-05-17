package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.entity.Transaction;
import com.aqa.homeworks.utils.PropertiesManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.aqa.homeworks.db.DBConstants.DB_CONNECTION;

public class TransactionImpl implements TransactionDao {

    @Override
    public void create(Transaction transaction) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement createTransaction = connection.prepareStatement(PropertiesManager.getQueryProperty("createTransaction"))) {
            checkPositiveBalanceAfterTransaction(transaction);
            createTransaction.setInt(1, transaction.getAccountId());
            createTransaction.setDouble(2, transaction.getAmount());
            createTransaction.execute();
            updateBalance(transaction);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateBalance(Transaction transaction) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement updateBalance = connection.prepareStatement(PropertiesManager.getQueryProperty("updateBalance"))) {
            updateBalance.setInt(1, transaction.getAccountId());
            updateBalance.setDouble(2, transaction.getAccountId());
            updateBalance.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactionsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAllTransactions = connection.prepareStatement(PropertiesManager.getQueryProperty("getAllTransactions"))) {
            ResultSet resultSet = getAllTransactions.executeQuery();
            while (resultSet.next()) {
                int transactionID = resultSet.getInt("transactionID");
                int accountID = resultSet.getInt("accountID");
                double amount = resultSet.getDouble("amount");
                transactionsList.add(new Transaction(transactionID, accountID, amount));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactionsList;
    }

    @Override
    public void createTopUpTransaction(Transaction transaction) {
        checkTransactionAmount(transaction);
        create(transaction);
    }

    @Override
    public void createWithDrawTransaction(Transaction transaction) {
        checkTransactionAmount(transaction);
        transaction.setAmount(transaction.getAmount() * -1);
        create(transaction);
    }

    private void checkTransactionAmount(Transaction transaction) {
        if (transaction.getAmount() <= 0) {
            throw new RuntimeException();
        }
    }

    private void checkPositiveBalanceAfterTransaction(Transaction transaction) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAccountBalance = connection.prepareStatement(PropertiesManager.getQueryProperty("getAccountBalance"))) {
            getAccountBalance.setInt(1, transaction.getAccountId());
            ResultSet resultSet = getAccountBalance.executeQuery();
            double currentBalance = resultSet.getDouble("balance");
            checkTransactionAmount(new Transaction(currentBalance + transaction.getAmount()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
