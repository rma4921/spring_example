package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Team;
import com.estsoft.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    // 전체 값 가져오기
    @GetMapping("/teams")
    public List<TeamDTO> selectAllTeams() {
        List<Team> teamAll = teamService.findAllTeams();
        return teamAll.stream().map(TeamDTO::new).
                toList();
    }

    // 특정 id 값 가져오기
    @GetMapping("/teams/{id}")
    public TeamDTO selectTeamById(@PathVariable Long id) {
        Team team = teamService.selectTeamById(id);
        return new TeamDTO(team);
    }

    // row 추가하기
    @PostMapping("/teams")
    public Team saveTeam(@RequestBody Team team) {
        return teamService.insertTeam(team);
    }

    // 데이터 수정하기
    @PutMapping("/teams/{id}")
    public TeamDTO updateTeam(@PathVariable Long id, @RequestBody Team newTeam) {
        Team team = teamService.updateTeam(id, newTeam.getName());
        return new TeamDTO(team);
    }

    // 데이터 삭제하기
    @DeleteMapping("/teams/{id}")
    public String deleteStudent(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return "success";
    }
}
