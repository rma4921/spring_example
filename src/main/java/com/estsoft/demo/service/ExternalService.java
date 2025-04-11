package com.estsoft.demo.service;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.estsoft.demo.dto.PostContent;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ExternalService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final BlogRepository blogRepository;

    public ExternalService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void call() {
        String url = "https://jsonplaceholder.typicode.com/posts";

        // RestTemplate
        // RestTemplate restTemplate = new RestTemplate(); // 스프링에서 관리해서 의존성 주입으로 사용해도 됨.
        ResponseEntity<List<PostContent>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});

        log.info("code: {}", response.getStatusCode()); // 200
        List<PostContent> postContent = response.getBody();
        log.info("postContent: {}", postContent); // id가 여러개
    }

    public void save() {
        String url = "https://jsonplaceholder.typicode.com/posts";

        ResponseEntity<List<AddArticleRequest>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AddArticleRequest>>() {}
        );
        List<AddArticleRequest> articles = response.getBody();
        // log.info("데이터 개수: {}", articles.size());
        for (AddArticleRequest dto : articles) {
            // log.info("title: {}, content: {}", dto.getTitle(), dto.getContent());
            Article article = dto.toEntity();
            blogRepository.save(article);
            // log.info("Saved article: {}", article.getTitle());
        }
    }
}
