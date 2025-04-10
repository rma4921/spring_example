package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.ArticleResponse;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estsoft.demo.blog.domain.Article;

import java.util.List;

@RestController
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // POST /api/articles
    // {title: "", content: ""}
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.saveArticle(request);

        // Article -> ArticleResponse로 변환 후 리턴
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedArticle.toDTO());
    }

    // GET /api/articles
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<Article> articles = blogService.findAllArticles();
        List<ArticleResponse> responseBody = articles.stream()
                .map(article -> new ArticleResponse(article.getId(), article.getTitle(), article.getContent()))
                .toList();
        return ResponseEntity.ok(responseBody);
    }

    // Get /api/articles/{id}
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticleById(@PathVariable Long id) {
        Article article = blogService.findArticleById(id);
        // ArticleResponse response = new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
        ArticleResponse response = article.toDTO();
        return ResponseEntity.ok(response);
    }

    // DELETE /api/articles/{id}
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticleByID(@PathVariable Long id) {
        blogService.deleteArticleById(id);
        return ResponseEntity.ok().build();
    }

    // DELETE /api/articles
    @DeleteMapping("/api/articles")
    public ResponseEntity<Void> deleteAllArticles() {
        blogService.deleteAllArticles();
        return ResponseEntity.ok().build();
    }

    // PUT /api/articles/{id}
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id,
                                                         @RequestBody UpdateArticleRequest request) {
        Article article = blogService.updateArticle(id, request);
        ArticleResponse response = article.toDTO();
        return ResponseEntity.ok(response);
    }

    // PUT의 ID가 잘못됐을 때, Exception 처리
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
