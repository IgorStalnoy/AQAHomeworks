package com.aqa.homeworks.ui;

import com.aqa.homeworks.buisness.Account;
import com.aqa.homeworks.buisness.Transaction;
import com.aqa.homeworks.buisness.User;
import com.aqa.homeworks.db.dbimplementation.*;
import com.aqa.homeworks.utils.ExceptionManager;

import java.util.List;
import java.util.Scanner;

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
        System.out.println("""
                Please enter an operation number:
                1 - Register new user
                2 - Enter user's menu""");
        do {
            validValue = scanner.hasNextInt();
            if (!validValue) {
                System.out.println("Not a number, please input an operation number");
                scanner.nextLine();
            } else {
                operationNumber = scanner.nextInt();
                if (operationNumber < 1 || operationNumber > 4) {
                    validValue = false;
                    System.out.println("Operation number should be between 1 and 2");
                    scanner.nextLine();
                }
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
            System.out.println("Please enter username: ");
            String username = scanner.nextLine();
            System.out.println("Please enter address or press enter key to skip:");
            String address = scanner.nextLine();
            User user = new User(username, address);
            isCreated = ExceptionManager.execute(userDAO::create, user);
            if (!isCreated) {
                System.out.println("User with inputted username already exist, please input another");
            }
        }
        startMenu();
    }

    private void chooseUserMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available users: ");
        List<User> usersList = userDAO.getAll();
        if (usersList.size() == 0) {
            System.out.println("There are no registered users, please register first");
            startMenu();
        }
        usersList.forEach(System.out::println);
        System.out.println("Please input username for login :");
        boolean isUserExist = false;
        User foundUser = null;
        while (!isUserExist) {
            String inputUserName = scanner.nextLine();
            foundUser = userDAO.getUserByName(inputUserName);
            if (foundUser == null) {
                System.out.println("Inputted username does not exist, please try again");
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
        System.out.println("""
                Please enter an operation number:
                1 - Register new account
                2 - Choose payment account
                3 - Return to main menu""");
        do {
            validValue = scanner.hasNextInt();
            if (!validValue) {
                System.out.println("Not a number, please input an operation number");
                scanner.nextLine();
            } else {
                operationNumber = scanner.nextInt();
                if (operationNumber < 1 || operationNumber > 3) {
                    validValue = false;
                    System.out.println("Operation number should be between 1 and 2");
                    scanner.nextLine();
                }
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
            System.out.println("Please input currency for your account");
            String currency = scanner.nextLine();
            Account account = new Account(loggedUser.getId(), currency);
            isCreated = ExceptionManager.execute(accountDAO::create, account);
            if (!isCreated) {
                System.out.println("Account with inputted currency already exist, please enter another");
            }
        }
        userMenu();
    }

    private void choosePaymentAccountMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available accounts: ");
        List<Account> accountList = accountDAO.getAccountListByUserId(loggedUser.getId());
        if (accountList.size() == 0) {
            System.out.println("There are no created account for this user, please create first");
            userMenu();
        }
        accountList.forEach(System.out::println);
        System.out.println("Please input accountID for creating money operation :");
        boolean isAccountExist = false;
        Account foundAccount = null;
        while (!isAccountExist) {
            int inputAccountID = scanner.nextInt();
            foundAccount = accountDAO.getAccountByAccountID(inputAccountID, loggedUser.getId());
            if (foundAccount == null) {
                System.out.println("Inputted accountID does not exist, please try again");
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
        System.out.println("Chosen account : \n" + choosedPaymentAccount.toString());
        int operationNumber;
        System.out.println("""
                Please enter an operation number:
                1 - Top up your account
                2 - Withdrawal from account
                3 - Return to user's menu""");
        do {
            validValue = scanner.hasNextInt();
            if (!validValue) {
                System.out.println("Not a number, please input an operation number");
                scanner.nextLine();
            } else {
                operationNumber = scanner.nextInt();
                if (operationNumber < 1 || operationNumber > 3) {
                    validValue = false;
                    System.out.println("Operation number should be between 1 and 2");
                    scanner.nextLine();
                }
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
            System.out.println("Please inter sum to top up: ");
            double topUpSum = scanner.nextDouble();
            isCreated = ExceptionManager.execute(transactionDao::createTopUpTransaction, new Transaction(choosedPaymentAccount.getId(), topUpSum));
            if (!isCreated) {
                System.out.println("The entered amount is greater than 100 000 000, below zero " +
                        " or the balance after replenishment is greater than 2 000 000 000, please enter a correct amount");
            }
            choosedPaymentAccount = accountDAO.getAccountByAccountID(choosedPaymentAccount.getId(), loggedUser.getId());
        }
        operationMenu();
    }

    private void withdrawMoney() {
        Scanner scanner = new Scanner(System.in);
        boolean isCreated = false;
        while (!isCreated) {
            System.out.println("Please inter sum to withdraw: ");
            int withDrawSum = scanner.nextInt();
            isCreated = ExceptionManager.execute(transactionDao::createWithDrawTransaction, new Transaction(choosedPaymentAccount.getId(), withDrawSum));
            if (!isCreated) {
                System.out.println("The entered amount is greater than 100 000 000, below zero " +
                        " or the balance after replenishment below 0, please enter a correct amount");
            }
            choosedPaymentAccount = accountDAO.getAccountByAccountID(choosedPaymentAccount.getId(), loggedUser.getId());
        }
        operationMenu();
    }
}

