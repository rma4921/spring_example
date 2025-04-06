package com.estsoft.demo.service;

import com.estsoft.demo.repository.Team;
import com.estsoft.demo.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    // 모든 팀 찾기
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    // 특정 팀 찾기
    public Team selectTeamById(Long id) {
        Optional<Team> optTeam = teamRepository.findById(id);
        return optTeam.orElseGet(Team::new);
    }

    // 정보 추가
    public Team insertTeam(Team team) {
        return teamRepository.save(team);
    }

    // 정보 수정
    @Transactional
    public Team updateTeam(Long id, String name) {
        Optional<Team> optTeam = teamRepository.findById(id)
                .map(team -> {
                    team.changeName(name);
                    return team;
                });
        return optTeam.orElseGet(Team::new);
    }

    // 정보 삭제
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }
}
