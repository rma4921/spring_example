package com.estsoft.demo.tdd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Account {
    private Long balance;

    public Account(Long balance) {
        this.balance = balance;
    }

    public Long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("출금 오류");
        }
        balance -= amount;
    }
}
