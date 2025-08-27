package com.sofka.sofkatest.account;

import com.sofka.sofkatest.auditing.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Account extends AuditableEntity<Long> {
    private String accountNumber;

    /*Como es un poco raro tener en base el valor de un type como string como se decia en el documento mejor lo trate
    como enum*/
    private AccountType accountType;
    private BigDecimal initialBalance;

    public Account() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
}
