package com.Banking_Service.Demo;

import com.Banking_Service.Demo.Interface.AccountService;
import com.Banking_Service.Demo.Service.Account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AccountTest {
    private Account account;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        account = new Account();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreamrestoreStreamss() {
        System.setOut(originalOut);
    }

    @Test
    public void testDepositIncreasesBalance() {
        account.deposit(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    public void testWithdrawalDecreasesBalance() {
        account.deposit(500);
        account.withdraw(200);
        assertEquals(300, account.getBalance());
    }

    @Test
    public void testDepositWithInvalidAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100);
        });
    }

    @Test
    public void testWithdrawWithInvalidAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-100);
        });
    }

    @Test
    public void testBalanceCalculationWithMultipleTransactions() {
        account.deposit(1000);
        account.withdraw(250);
        account.deposit(500);
        account.withdraw(100);

        assertEquals(1150, account.getBalance());
    }

    @Test
    public void testPublicInterfaceMethods() {
        AccountService accountService = new Account();

        accountService.deposit(100);
        accountService.withdraw(50);
        accountService.printStatement();

        assertTrue(outContent.toString().contains("Date       || Amount || Balance"));
    }
}