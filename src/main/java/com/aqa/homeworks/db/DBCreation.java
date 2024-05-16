package com.aqa.homeworks.db;

import com.aqa.homeworks.utils.QueriesPropertiesManager;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.aqa.homeworks.db.DBConstants.DB_NAME;
import static com.aqa.homeworks.db.DBConstants.DB_PATH;

public class DBCreation {
    private static final Properties queries = QueriesPropertiesManager.getProperties();

    private static boolean isDatabaseExist() {
        File file = new File(DB_PATH + DB_NAME);
        return file.exists();
    }

    public static void createDatabase() throws SQLException {
        if (!isDatabaseExist()) {
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement createFirstTable = connection.prepareStatement(queries.getProperty("createUsersTable"));
                 PreparedStatement createSecondTable = connection.prepareStatement(queries.getProperty("createAccountsTable"));
                 PreparedStatement createThirdTable = connection.prepareStatement(queries.getProperty("createTransactionsTable"))) {
                createFirstTable.execute();
                createSecondTable.execute();
                createThirdTable.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
