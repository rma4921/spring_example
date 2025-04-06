package com.estsoft.demo.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @Column(name = "team_Id", updatable = false, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // 1-n 주인 X, member의 team이 주인
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public void changeName(String name) {
        this.name = name;
    }
}
