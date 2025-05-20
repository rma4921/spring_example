package com.estsoft.demo.blog.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.ArticleResponse;
import com.estsoft.demo.blog.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest2 {

    @InjectMocks
    private BlogController blogController;

    @Mock
    private BlogService blogService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    @Test
    public void testSaveArticle() throws Exception {
        String jsonContent = """
            {
                "title": "제목",
                "content" : "내용"
            }
            """;
        Mockito.when(blogService.saveArticle(any())).thenReturn(new Article("제목", "내용"));

        ResultActions resultActions = mockMvc.perform(
            post("/api/articles").contentType(MediaType.APPLICATION_JSON).content(jsonContent));

        resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("제목"))
            .andExpect(jsonPath("$.content").value("내용"));
    }
}