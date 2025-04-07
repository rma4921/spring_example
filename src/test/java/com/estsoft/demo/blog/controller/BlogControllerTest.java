package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void saveUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @Test
    // POST /api/articles
    void saveArticle() throws Exception {
        // given : ObjectMapper로 직렬화. Object -> json
        AddArticleRequest request = new AddArticleRequest("제목", "내용");
        String requestBody = objectMapper.writeValueAsString(request);
        // System.out.println("requestBody: " + requestBody);
        /*
        String requestBody = """
            {
                "title" : "제목",
            "   content" : "내용"
            }
            """;
         */

        // when : API 요청
        ResultActions result = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));

        System.out.println("종료");
    }

    // 전체 목록 조회 테스트 코드
    @Test
    public void findAllArticles() throws Exception {
        // given
        Article savedArticle = Article.builder()
                .title("테스터")
                .content("확인")
                .build();
        blogRepository.save(savedArticle);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$[0].content").value(savedArticle.getContent()));
    }

    // 단 건 조회 테스트 코드
    @Test
    public void findArticleById() throws Exception {
        // given: 테스트용 데이터
        Article savedArticle = Article.builder()
                .title("단 건 테스트")
                .content("테스터")
                .build();
        blogRepository.save(savedArticle);

        Long articleId = savedArticle.getId();

        // when : 저장된 id 값으로 GET
        // ResultActions resultActions = mockMvc.perform(get("/api/articles/" + articleId));
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", articleId));

        // then : 저장한 값하고 일치하는 지 확인
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(articleId))
                .andExpect(jsonPath("$.title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$.content").value(savedArticle.getContent()));
    }
}