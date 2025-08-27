package com.sofka.sofkatest.transaction;

import com.sofka.sofkatest.auditing.AuditableEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction extends AuditableEntity<Long> {

    private LocalDateTime timestamp;
    private TransactionType transactionType;
    private BigDecimal value;
    private BigDecimal balance;

    public Transaction() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
