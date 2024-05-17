package com.aqa.homeworks.ui;

import com.aqa.homeworks.db.dbimplementation.*;
import com.aqa.homeworks.entity.Account;
import com.aqa.homeworks.entity.Transaction;
import com.aqa.homeworks.entity.User;
import com.aqa.homeworks.utils.ExceptionManager;

import java.util.List;
import java.util.Scanner;

import static com.aqa.homeworks.ui.UIConstants.*;

public class UserInterface {
    private User loggedUser;
    private Account choosedPaymentAccount;
    private final UserDao userDAO = new UserDaoImpl();
    private final AccountDao accountDAO = new AccountDaoImpl();
    private final TransactionDao transactionDao = new TransactionImpl();

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        if (loggedUser != null) {
            loggedUser = null;
        }
        int operationNumber;
        boolean validValue;
        System.out.println(START_MENU_TEXT);
        do {
            validValue = isNumberInputted(scanner);
            if (validValue) {
                operationNumber = scanner.nextInt();
                validValue = isMenuNumberExist(scanner, operationNumber, START_MENU_ACTIONS_COUNT);
                switch (operationNumber) {
                    case 1:
                        createUserMenu();
                    case 2:
                        chooseUserMenu();
                }
            }
        } while (!validValue);
    }

    private void createUserMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isCreated = false;
        while (!isCreated) {
            System.out.println(CREATE_USER_MENU_USERNAME_INPUT_TEXT);
            String username = scanner.nextLine();
            System.out.println(CREATE_USER_MENU_ADDRESS_INPUT_TEXT);
            String address = scanner.nextLine();
            User user = new User(username, address);
            isCreated = ExceptionManager.execute(userDAO::create, user);
            if (!isCreated) {
                System.out.println(CREATE_USER_MENU_ERROR_TEXT);
            }
        }
        startMenu();
    }

    private void chooseUserMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CHOOSE_USER_MENU_TEXT);
        List<User> usersList = userDAO.getAll();
        if (usersList.size() == 0) {
            System.out.println(CHOOSE_USER_MENU_NO_USERS_ERROR_MESSAGE);
            startMenu();
        }
        usersList.forEach(System.out::println);
        System.out.println(CHOOSE_USER_MENU_SEARCH_USER_TEXT);
        boolean isUserExist = false;
        User foundUser = null;
        while (!isUserExist) {
            String inputUserName = scanner.nextLine();
            foundUser = userDAO.getByName(inputUserName);
            if (foundUser == null) {
                System.out.println(CHOOSE_USER_MENU_USER_NOT_EXIST_ERROR_TEXT);
            } else {
                isUserExist = true;
            }
        }
        loggedUser = foundUser;
        userMenu();
    }

    private void userMenu() {
        if (choosedPaymentAccount != null) {
            choosedPaymentAccount = null;
        }
        Scanner scanner = new Scanner(System.in);
        boolean validValue;
        int operationNumber;
        System.out.println(USER_MENU_TEXT);
        do {
            validValue = isNumberInputted(scanner);
            if (validValue) {
                operationNumber = scanner.nextInt();
                validValue = isMenuNumberExist(scanner, operationNumber, USER_MENU_ACTIONS_COUNT);
                switch (operationNumber) {
                    case 1:
                        createAccountMenu();
                    case 2:
                        choosePaymentAccountMenu();
                    case 3:
                        startMenu();
                }
            }
        } while (!validValue);
    }

    private void createAccountMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isCreated = false;
        while (!isCreated) {
            System.out.println(CREATE_ACCOUNT_MENU_INPUT_CURRENCY_TEXT);
            String currency = scanner.nextLine();
            Account account = new Account(loggedUser.getId(), currency);
            isCreated = ExceptionManager.execute(accountDAO::create, account);
            if (!isCreated) {
                System.out.println(CREATE_ACCOUNT_MENU_CURRENCY_ALREADY_EXIST_ERROR_TEXT);
            }
        }
        userMenu();
    }

    private void choosePaymentAccountMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CHOOSE_ACCOUNT_MENU_TEXT);
        List<Account> accountList = accountDAO.getAllByUserId(loggedUser.getId());
        if (accountList.size() == 0) {
            System.out.println(CHOOSE_ACCOUNT_MENU_NO_ACCOUNT_ERROR_TEXT);
            userMenu();
        }
        accountList.forEach(System.out::println);
        System.out.println(CHOOSE_ACCOUNT_MENU_INPUT_ID_TEXT);
        boolean isAccountExist = false;
        Account foundAccount = null;
        while (!isAccountExist) {
            boolean validValue = false;
            while (!validValue) {
                validValue = isNumberInputted(scanner);
            }
            int inputAccountID = scanner.nextInt();
            foundAccount = accountDAO.getByID(inputAccountID);
            if (foundAccount == null) {
                System.out.println(CHOOSE_ACCOUNT_MENU_NOT_EXIST_ERROR_TEXT);
            } else {
                isAccountExist = true;
            }
        }
        choosedPaymentAccount = foundAccount;
        operationMenu();
    }

    private void operationMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean validValue;
        System.out.println(OPERATION_MENU_CHOOSED_ACCOUNT_TEXT + choosedPaymentAccount.toString());
        int operationNumber;
        System.out.println(OPERATION_MENU_TEXT);
        do {
            validValue = isNumberInputted(scanner);
            if (validValue) {
                operationNumber = scanner.nextInt();
                validValue = isMenuNumberExist(scanner, operationNumber, OPERATION_MENU_ACTIONS_COUNT);
                switch (operationNumber) {
                    case 1:
                        topUpAccount();
                    case 2:
                        withdrawMoney();
                    case 3:
                        choosedPaymentAccount = null;
                        userMenu();
                }
            }
        } while (!validValue);
    }

    private void topUpAccount() {
        Scanner scanner = new Scanner(System.in);
        boolean isCreated = false;
        while (!isCreated) {
            System.out.println(TOP_UP_MENU_TEXT);
            double topUpSum = scanner.nextDouble();
            Transaction transaction = new Transaction(choosedPaymentAccount.getId(), topUpSum);
            isCreated = ExceptionManager.execute(transactionDao::createTopUpTransaction, transaction);
            printTransactionError(isCreated);
            choosedPaymentAccount = accountDAO.getByID(choosedPaymentAccount.getId());
        }
        operationMenu();
    }

    private void withdrawMoney() {
        Scanner scanner = new Scanner(System.in);
        boolean isCreated = false;
        while (!isCreated) {
            System.out.println(WITHDRAW_MENU_TEXT);
            int withDrawSum = scanner.nextInt();
            Transaction transaction = new Transaction(choosedPaymentAccount.getId(), withDrawSum);
            isCreated = ExceptionManager.execute(transactionDao::createWithDrawTransaction, transaction);
            printTransactionError(isCreated);
            choosedPaymentAccount = accountDAO.getByID(choosedPaymentAccount.getId());
        }
        operationMenu();
    }

    private boolean isMenuNumberExist(Scanner scanner, int operationNumber, int actionsCount) {
        boolean validValue = true;
        if (operationNumber < 1 || operationNumber > actionsCount) {
            validValue = false;
            System.out.println(INPUTTED_MENU_NOT_EXIST_ERROR_TEXT + actionsCount);
            scanner.nextLine();
        }
        return validValue;
    }

    private boolean isNumberInputted(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return true;
        }
        System.out.println(NOT_A_NUMBER_ERROR_TEXT);
        scanner.nextLine();
        return false;
    }

    private void printTransactionError(boolean isCreated) {
        if (!isCreated) {
            System.out.println(TRANSACTION_CANT_CREATED_ERROR_TEXT);
        }

    }

}

