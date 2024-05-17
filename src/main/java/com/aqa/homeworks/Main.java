package com.aqa.homeworks;

import com.aqa.homeworks.db.DBCreation;
import com.aqa.homeworks.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        DBCreation.createDatabase();
        UserInterface userInterface = new UserInterface();
        userInterface.startMenu();
    }
}
