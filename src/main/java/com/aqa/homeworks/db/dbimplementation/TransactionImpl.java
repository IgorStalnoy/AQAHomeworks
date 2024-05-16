package com.aqa.homeworks.db.dbimplementation;

import com.aqa.homeworks.buisness.Transaction;
import com.aqa.homeworks.utils.QueriesPropertiesManager;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import static com.aqa.homeworks.db.DBConstants.DB_CONNECTION;

public class TransactionImpl implements TransactionDao {
    Properties queries = QueriesPropertiesManager.getProperties();

    @Override
    public void create(Transaction transaction) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement createTransaction = connection.prepareStatement(queries.getProperty("createTransaction"))) {
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
             PreparedStatement updateBalance = connection.prepareStatement(queries.getProperty("updateBalance"))) {
            updateBalance.setInt(1, transaction.getAccountId());
            updateBalance.setDouble(2, transaction.getAccountId());
            updateBalance.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getAll() {
        return null;
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
             PreparedStatement getAccountBalance = connection.prepareStatement(queries.getProperty("getAccountBalance"))) {
            getAccountBalance.setInt(1, transaction.getAccountId());
            ResultSet resultSet = getAccountBalance.executeQuery();
            double currentBalance = resultSet.getDouble("balance");
            if (currentBalance + transaction.getAmount() < 0) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
