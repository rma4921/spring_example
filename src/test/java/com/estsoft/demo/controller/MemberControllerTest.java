package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import com.estsoft.demo.repository.Team;
import com.estsoft.demo.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        memberRepository.deleteAll();
    }

//    @Disabled
    @DisplayName("member 조회 검증")
    @Test
    void showMembers() throws Exception{

        // 2. given
        Team team = teamRepository.findById(1L).orElseGet(Team::new);
        Member member = new Member("바보", team);
        memberRepository.save(member);

        // 1. when Get /members 호출
        ResultActions resultActions = mockMvc.perform(get("/members"));

        // 3. then /members API 호출에 대한 응답 결과가 given절에서 넣어준 데이터와 같다는 검증
        /*
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(member.getId()))
                .andExpect(jsonPath("$[0].name").value(member.getName()));

         */
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(member.getId()))
                .andExpect(jsonPath("$[0].name").value(member.getName()));
    }

    @DisplayName("member 단건 조회")
    @Test
    public void showMember() throws Exception{
        // given
        Team team = teamRepository.findById(1L).orElseGet(Team::new);
        Member member = new Member("메시", team);
        Member savedMember = memberRepository.save(member);

        // when
        ResultActions resultActions = mockMvc.perform(get("/members/{id}", savedMember.getId()));

        // then
        resultActions.andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(savedMember.getId()))
            .andExpect(jsonPath("$.name").value(savedMember.getName()));
    }

    @Disabled
    @DisplayName("member 저장 테스트")
    @Test
    public void saveMember() throws Exception {

        String content = """
                { "name": "메시",
                  "team": {
                    "id": 1,
                    "name": "FC바르셀로나"
                  }
                }
            """;

        // when
        ResultActions resultActions = mockMvc.perform(post("/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content));

        // then
        resultActions.andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("메시"))
            .andExpect(jsonPath("$.teamDTO.teamId").value(1))
            .andExpect(jsonPath("$.teamDTO.name").value("FC바르셀로나"));
    }

}