package com.aqa.homeworks.ui;

public class UIConstants {

    public final static int START_MENU_ACTIONS_COUNT = 2;
    public final static int USER_MENU_ACTIONS_COUNT = 3;
    public final static int OPERATION_MENU_ACTIONS_COUNT = 3;
    public static final String START_MENU_TEXT = """
            Please enter an operation number:
            1 - Register new user
            2 - Enter user's menu""";
    public static final String CREATE_USER_MENU_USERNAME_INPUT_TEXT = "Please enter username: ";
    public static final String CREATE_USER_MENU_ADDRESS_INPUT_TEXT = "Please enter address or press enter key to skip: ";
    public static final String CREATE_USER_MENU_ERROR_TEXT = "User with inputted username already exist, please input another";
    public static final String CHOOSE_USER_MENU_TEXT = "Available users: ";
    public static final String CHOOSE_USER_MENU_NO_USERS_ERROR_MESSAGE = "There are no registered users, please register first";
    public static final String CHOOSE_USER_MENU_SEARCH_USER_TEXT = "Please input username for login :";
    public static final String CHOOSE_USER_MENU_USER_NOT_EXIST_ERROR_TEXT = "Inputted username does not exist, please try again";
    public static final String USER_MENU_TEXT = """
            Please enter an operation number:
            1 - Register new account
            2 - Choose payment account
            3 - Return to main menu""";
    public static final String CREATE_ACCOUNT_MENU_INPUT_CURRENCY_TEXT = "Please input currency for your account";
    public static final String CREATE_ACCOUNT_MENU_CURRENCY_ALREADY_EXIST_ERROR_TEXT = "Account with inputted currency already exist, please enter another";
    public static final String CHOOSE_ACCOUNT_MENU_TEXT = "Available accounts: ";
    public static final String CHOOSE_ACCOUNT_MENU_NO_ACCOUNT_ERROR_TEXT = "There are no created account for this user, please create first";
    public static final String CHOOSE_ACCOUNT_MENU_INPUT_ID_TEXT = "Please input accountID for creating money operation :";
    public static final String CHOOSE_ACCOUNT_MENU_NOT_EXIST_ERROR_TEXT = "Inputted accountID does not exist, please try again";
    public static final String OPERATION_MENU_CHOOSED_ACCOUNT_TEXT = "Chosen account : \n";
    public static final String OPERATION_MENU_TEXT = """
            Please enter an operation number:
            1 - Top up your account
            2 - Withdrawal from account
            3 - Return to user's menu""";
    public static final String TOP_UP_MENU_TEXT = "Please inter sum to top up: ";
    public static final String WITHDRAW_MENU_TEXT = "Please inter sum to withdraw: ";
    public static final String INPUTTED_MENU_NOT_EXIST_ERROR_TEXT = "Operation number should be between 1 and ";
    public static final String NOT_A_NUMBER_ERROR_TEXT = "Not a number, please input an operation number";
    public static final String TRANSACTION_CANT_CREATED_ERROR_TEXT = "The entered amount is greater than 100 000 000, below zero " +
            " or the balance after replenishment is greater than 2 000 000 000, please enter a correct amount";
}
