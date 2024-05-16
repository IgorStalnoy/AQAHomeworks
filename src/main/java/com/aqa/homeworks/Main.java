package com.aqa.homeworks;

import com.aqa.homeworks.db.DBCreation;
import com.aqa.homeworks.ui.UserInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DBCreation.createDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UserInterface userInterface = new UserInterface();
        userInterface.startMenu();
    }
}
