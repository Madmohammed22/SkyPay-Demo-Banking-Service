package com.Banking_Service.Demo;

import com.Banking_Service.Demo.Interface.AccountService;
import com.Banking_Service.Demo.Service.Account;

import java.time.LocalDate;

public class DemoApplication {

    public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
        System.out.println("=== Banking Service Application ===\n");
        {
            AccountService account = new Account();
            System.out.println("Example 1: Current Date Transactions");
            account.deposit(500);
            account.withdraw(200);
            account.deposit(1000);
            account.printStatement();

        }
        System.out.println("\n" + "=".repeat(50) + "\n");
        {
            System.out.println("Example 2: Acceptance Test (specific dates)\n");
            Account testAccount = new Account();
            testAccount.depositOnDate(1000, LocalDate.of(2012, 1, 10));
            testAccount.depositOnDate(2000, LocalDate.of(2012, 1, 13));
            testAccount.withdrawOnDate(500, LocalDate.of(2012, 1, 14));
            testAccount.printStatement();
            System.out.println("Final Balance: " + testAccount.getBalance());
        }


        System.out.println("\n" + "=".repeat(50));
        {
            System.out.println("\nExample 3: Exception Handling\n");

            Account exceptionAccount = new Account();

            try {
                exceptionAccount.deposit(-100);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught exception for negative deposit: " + e.getMessage());
            }

            try {
                exceptionAccount.withdraw(0);
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Caught exception for zero withdrawal: " + e.getMessage());
            }

            System.out.println("\n=== All examples completed successfully! ===");
        }

    }
}
