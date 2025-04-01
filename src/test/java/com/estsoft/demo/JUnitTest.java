package com.estsoft.demo;

//import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @Test
    public void addTest() {
        int a = 1;
        int b = 2;
        int result = 3;

        assertEquals(result, a + b);
        // Assertions.assertEquals(result, a + b);
        // Assertions.assertEquals(4, a + b); // 오류
    }
}
