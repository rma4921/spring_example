package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Team;
import lombok.Getter;

import java.util.List;

@Getter
public class TeamDTO {
    private Long teamId;
    private String name;

    public TeamDTO(Team team) {
        teamId = team.getId();
        name = team.getName();
    }
}
