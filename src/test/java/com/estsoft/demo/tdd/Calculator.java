package com.estsoft.demo.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Calculator {
    @Test
    public void testSum() {
        assertEquals(3, sum(1, 2));
        assertEquals(52, sum(50, 2));
        assertEquals(41, sum(19, 22));
    }

    private int sum(int a, int b) {
        return a + b;
    }
}
