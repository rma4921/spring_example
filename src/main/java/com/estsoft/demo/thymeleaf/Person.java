package com.estsoft.demo.thymeleaf;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private int age;
    private List<String> hobbies;
}
