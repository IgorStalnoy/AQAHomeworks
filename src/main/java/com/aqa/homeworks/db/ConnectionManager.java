package com.aqa.homeworks.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.aqa.homeworks.db.DBConstants.DB_CONNECTION;

public class ConnectionManager {

    public static Connection connection;

    private ConnectionManager() {
    }

    private static void initConnection() {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        if(connection == null) {
            initConnection();
        }
        return connection;
    }

}
