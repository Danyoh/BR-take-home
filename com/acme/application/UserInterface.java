package com.acme.application;

import java.util.Scanner;
import com.acme.account.TransactionType;
import java.math.BigDecimal;
import com.acme.account.Account;
import com.acme.account.TransactionalAccount;
import java.math.RoundingMode;
import java.util.InputMismatchException;

public class UserInterface {
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        BigDecimal depositAmount;
        BigDecimal withdrawAmount;

        // Create a new account with an initial balance of $1000
        Account account = new TransactionalAccount(new BigDecimal("1000"));

        while (true) {
            System.out.println("\n\n-- Acme Financial Menu --");
            System.out.println("1. Cash - Deposit");
            System.out.println("2. Cash - Withdraw");
            System.out.println("3. Stock - Deposit");
            System.out.println("4. Stock - Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Get History");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            //If put characters it will cause error

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid menu choice. Please enter a number from 1-7.");
                scanner.nextLine();
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Cash - Enter amount to deposit: ");
                        depositAmount = new BigDecimal(scanner.next());
                        account.deposit(depositAmount, TransactionType.CASH);
                        break;

                    case 2:
                        System.out.print("Cash - Enter amount to withdraw: ");
                        withdrawAmount = new BigDecimal(scanner.next());
                        account.withdraw(withdrawAmount, TransactionType.CASH);
                        break;
                        
                    case 3:
                        System.out.print("Stock - Enter amount to deposit: ");
                        depositAmount = new BigDecimal(scanner.next());
                        account.deposit(depositAmount, TransactionType.STOCK);
                        break;

                    case 4:
                        System.out.print("Stock - Enter amount to withdraw: ");
                        withdrawAmount = new BigDecimal(scanner.next());
                        account.withdraw(withdrawAmount, TransactionType.STOCK);
                        break;

                    case 5:
                        System.out.println("Cash Balance: $" + account.getCashBalance().setScale(2, RoundingMode.HALF_UP));
                        System.out.println("Stock Balance: $" + account.getStockBalance().setScale(2, RoundingMode.HALF_UP));
                        System.out.println("Total Balance: $" + account.getBalance().setScale(2, RoundingMode.HALF_UP));
                        break;
                        
                    case 6:
                        System.out.print("Transaction History: \n" + account.getHistory());
                        break;

                    case 7:
                        System.out.println("Exiting the program...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please choose a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid amount. Please only enter numeric values.");
            }
        }
        
    }
}