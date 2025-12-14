package com.Banking_Service.Demo.Service;

import java.time.LocalDate;

enum TransactionType {
    DEPOSIT,
    WITHDRAWAL
}

public class Transaction {
    private final TransactionType type;
    private final int amount;
    private final LocalDate date;

    public Transaction(TransactionType type, int amount, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
