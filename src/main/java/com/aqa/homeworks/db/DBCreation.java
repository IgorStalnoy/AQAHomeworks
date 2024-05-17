package com.aqa.homeworks.db;

import com.aqa.homeworks.utils.PropertiesManager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.aqa.homeworks.db.DBConstants.*;

public class DBCreation {

    private static boolean isDatabaseExist() {
        File file = new File(DB_PATH + DB_NAME);
        return file.exists();
    }

    public static void createDatabase() {
        if (!isDatabaseExist()) {
            try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
                 PreparedStatement createFirstTable = connection.prepareStatement(PropertiesManager.getQueryProperty("createUsersTable"));
                 PreparedStatement createSecondTable = connection.prepareStatement(PropertiesManager.getQueryProperty("createAccountsTable"));
                 PreparedStatement createThirdTable = connection.prepareStatement(PropertiesManager.getQueryProperty("createTransactionsTable"))) {
                createFirstTable.execute();
                createSecondTable.execute();
                createThirdTable.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
