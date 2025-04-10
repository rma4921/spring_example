package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;

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

    @Test
    public void deleteArticleById() throws Exception {
        // given
        Article article = Article.builder()
                .content("testContent")
                .title("testTitle")
                .build();
        blogRepository.save(article);
        Long id = article.getId();

        //when
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

        //then
        resultActions.andExpect(status().isOk());

        List<Article> list = blogRepository.findAll();
        Assertions.assertThat(list).isEmpty();
        // Assertions.assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void deleteAllArticles() throws Exception {
        // given
        Article article1 = Article.builder()
                .title("title1")
                .content("content1")
                .build();
        Article article2 = Article.builder()
                .title("title2")
                .content("content2")
                .build();

        blogRepository.save(article1);
        blogRepository.save(article2);

        // when
        ResultActions resultActions = mockMvc.perform(delete("/api/articles"));

        // then
        resultActions.andExpect(status().isOk());

        List<Article> articles = blogRepository.findAll();
        Assertions.assertThat(articles).isEmpty();
    }

    @Test
    public void updateArticle() throws Exception {
        // given : 게시글 추가, id 추출, 수정할 값 세팅
        Article article = Article.builder()
                .title("title")
                .content("content")
                .build();
        blogRepository.save(article);
        Long id = article.getId();
        // request 생성 | 직렬화(object -> json)
        UpdateArticleRequest request = new UpdateArticleRequest("updateTitle", "updateContent");
        String requestBody = new ObjectMapper().writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));

        Article article1 = blogRepository.findById(id).orElseThrow();
        Assertions.assertThat(article1.getTitle()).isEqualTo(article.getTitle());
        Assertions.assertThat(article1.getContent()).isEqualTo(article.getContent());
    }

    @DisplayName("400 예외 확인 테스트")
    @Test
    public void updateArticleBadRequest() throws Exception {
        // given : 존재하지 않는 id 설정
        Long invalidId = 0L;
        UpdateArticleRequest request = new UpdateArticleRequest("updateTitle", "updateContent");
        String requestBody = new ObjectMapper().writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", invalidId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then : 400 BAD_REQUEST 응답 확인
        resultActions.andExpect(status().isBadRequest());
    }
}