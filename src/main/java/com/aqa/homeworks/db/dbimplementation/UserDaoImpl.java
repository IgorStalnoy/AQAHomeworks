package com.aqa.homeworks.db.dbimplementation;


import com.aqa.homeworks.buisness.User;
import com.aqa.homeworks.utils.QueriesPropertiesManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.aqa.homeworks.db.DBConstants.DB_CONNECTION;

public class UserDaoImpl implements UserDao {
    Properties queries = QueriesPropertiesManager.getProperties();

    @Override
    public void create(User user) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement createUser = connection.prepareStatement(queries.getProperty("createUser"))) {
            createUser.setString(1, user.getName());
            if(user.getAddress().equals("")) {
                createUser.setString(2, null);
            } else {
                createUser.setString(2, user.getAddress());
            }
            createUser.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByName(String username) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getUser = connection.prepareStatement(queries.getProperty("getUserByName"))) {
            getUser.setString(1, username);
            ResultSet resultSet = getUser.executeQuery();
            if (resultSet.next()) {
                String usernameResult = resultSet.getString("name");
                String addressResult = resultSet.getString("address");
                int userId = resultSet.getInt("userID");
                return new User(userId,usernameResult, addressResult);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getAllUsers = connection.prepareStatement(queries.getProperty("getAllUsers"))) {
            ResultSet resultSet = getAllUsers.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int userId = resultSet.getInt("userID");
                usersList.add(new User(userId,name, address));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }
}