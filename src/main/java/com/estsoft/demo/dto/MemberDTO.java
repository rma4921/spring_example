package com.estsoft.demo.dto;

import com.estsoft.demo.repository.Member;
import lombok.Getter;

@Getter
public class MemberDTO {
    private Long id;
    private String name;
    private TeamDTO teamDTO;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.teamDTO = new TeamDTO(member.getTeam());
    }
}
