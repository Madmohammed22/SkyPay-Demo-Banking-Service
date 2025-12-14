package com.Banking_Service.Demo.Service;

import com.Banking_Service.Demo.Interface.AccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Account implements AccountService {
    private final List<Transaction> transactions;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Account() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {
        validateAmount(amount);
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, LocalDate.now()));
    }

    @Override
    public void withdraw(int amount) {
        validateAmount(amount);
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, LocalDate.now()));
    }

    @Override
    public void printStatement() {
        System.out.println(generateStatement());
    }

    private String generateStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Date       || Amount || Balance\n");

        if (transactions.isEmpty()) {
            return statement.toString();
        }

        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        sortedTransactions.sort(Comparator.comparing(Transaction::getDate));

        int balance = 0;
        Map<Transaction, Integer> balanceMap = new LinkedHashMap<>();

        for (Transaction transaction : sortedTransactions) {
            if (transaction.getType() == TransactionType.DEPOSIT) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
            balanceMap.put(transaction, balance);
        }

        sortedTransactions.sort(Comparator.comparing(Transaction::getDate).reversed());

        for (Transaction transaction : sortedTransactions) {
            String dateStr = transaction.getDate().format(DATE_FORMATTER);
            String amountStr = formatAmount(transaction);
            int transactionBalance = balanceMap.get(transaction);

            statement.append(String.format("%s || %s || %d\n", dateStr, amountStr, transactionBalance));
        }

        return statement.toString();
    }

    public int getBalance() {
        return calculateBalance();
    }

    private int calculateBalance() {
        int balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.DEPOSIT) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }

    private String formatAmount(Transaction transaction) {
        String sign = transaction.getType() == TransactionType.DEPOSIT ? "+" : "-";
        return sign + transaction.getAmount();
    }

    public void depositOnDate(int amount, LocalDate date) {
        validateAmount(amount);
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, date));
    }

    public void withdrawOnDate(int amount, LocalDate date) {
        validateAmount(amount);
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, date));
    }
}

