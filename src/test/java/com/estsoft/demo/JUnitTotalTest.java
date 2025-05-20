package com.estsoft.demo;

import org.junit.jupiter.api.*;

public class JUnitTotalTest {
    @BeforeAll // 전체 테스트를 시작하기 전 1회
    public static void beforeAll() {
        System.out.println("@BeforeAll");
    }
    @BeforeEach // 각각의 테스트를 시작하기 전 1회
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }
    @AfterAll // 전체 테스트를 완료한 뒤 1회
    public static void afterAll() {
        System.out.println("@AfterAll");
    }
    @AfterEach // 각각의 테스트를 완료한 후 1회
    public void afterEach() {
        System.out.println("@AfterEach");
    }
    @Test
    public void test() {
        System.out.println("test1");
    }
    @Test
    public void test2() {
        System.out.println("test2");
    }
}
