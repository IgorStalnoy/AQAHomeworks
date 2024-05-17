package com.aqa.homeworks.db.dbimplementation;


import com.aqa.homeworks.entity.User;
import com.aqa.homeworks.utils.PropertiesManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.aqa.homeworks.db.DBConstants.DB_CONNECTION;

public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement createUser = connection.prepareStatement(PropertiesManager.getQueryProperty("createUser"))) {
            createUser.setString(1, user.getName());
            if (user.getAddress().equals("")) {
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
    public User getByName(String username) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION);
             PreparedStatement getUser = connection.prepareStatement(PropertiesManager.getQueryProperty("getUserByName"))) {
            getUser.setString(1, username);
            ResultSet resultSet = getUser.executeQuery();
            if (resultSet.next()) {
                return getUserFromResult(resultSet);
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
             PreparedStatement getAllUsers = connection.prepareStatement(PropertiesManager.getQueryProperty("getAllUsers"))) {
            ResultSet resultSet = getAllUsers.executeQuery();
            while (resultSet.next()) {
                usersList.add(getUserFromResult(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    private User getUserFromResult(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        int userId = resultSet.getInt("userID");
        return new User(userId, name, address);
    }
}