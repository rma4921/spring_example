package com.estsoft.demo.dto;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberRequest {
    private String name;
    private TeamRequest team;

    public Member toEntity() {
        return new Member(name, team.toEntity());
    }

    @Getter
    static class TeamRequest {
        private Long teamId;
        private String name;

        public Team toEntity() {
            return new Team(teamId, name);
        }
    }
}
