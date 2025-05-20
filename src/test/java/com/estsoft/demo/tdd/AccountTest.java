package com.estsoft.demo.tdd;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        assertNotNull(account);
    }

    @Test
    public void testGetBalance() throws Exception {
        Account account = new Account(1000000L);
        assertEquals(1000000L, account.getBalance());
//        if (account.getBalance() != 1000000L) {
//            throw new Exception("잔액 조회 실패!");
//        }

        account = new Account(100L);
        assertEquals(100L, account.getBalance());
    }

    @Test
    public void testDeposit() throws Exception {
        Account account = new Account(10000L);
        account.deposit(50000L);
        assertEquals(60000L, account.getBalance());
    }

    @Test
    @Disabled
    public void testWithdraw() throws Exception {
        Account account = new Account(10000L);
        account.withdraw(5000L);
        assertEquals(5000L, account.getBalance());

        assertThrowsExactly(RuntimeException.class, () -> account.withdraw(10000L));
    }
}
