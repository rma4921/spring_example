package com.estsoft.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {

    @Test
    public void test() {
        int a = 3;
        int b = 1;
        int result = 4;

        // Assertions.assertThat(a + b).isEqualTo(result); // static import를 사용하여 Assertions를 없앨 수 있다.
        assertThat(a + b).isEqualTo(result);
    }

}
