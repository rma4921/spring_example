package com.estsoft.demo.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // fk : team_id
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 : fetch = FetchType.LAZY
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public void changeName(String name) {
        this.name = name;
    }
//    @Temporal(TemporalType.TIMESTAMP)
//    private Timestamp createAt;
}
