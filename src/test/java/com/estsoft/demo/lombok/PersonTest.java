package com.estsoft.demo.lombok;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void testLombok() {
        Person person = new Person();
        person.setId(1L);
        person.setName("tester");
        person.setAge(27);
        person.setHobbies(Arrays.asList("게임", "노래"));

        System.out.println(person.getId());
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getHobbies());
    }
}