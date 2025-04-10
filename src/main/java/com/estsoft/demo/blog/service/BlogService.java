package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public Article saveArticle(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 전체 목록 조회
    public List<Article> findAllArticles() {
        return blogRepository.findAll();
    }

    public Article findArticleById(Long id) {
        Optional<Article> optArticle = blogRepository.findById(id);
        return optArticle.orElseGet(Article::new);
    }

    // 특정 글 삭제
    public void deleteArticleById(Long id) {
        blogRepository.deleteById(id);
    }

    // 전체 목록 삭제
    public void deleteAllArticles() {
        blogRepository.deleteAll();
    }

    // 글 수정
    @Transactional
    public Article updateArticle(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exists id: " + id)); // 500 Error

        article.update(request.getTitle(), request.getContent());

        return article;
        // return blogRepository.save(article); // @Transactional 대신 사용해도 됨.
    }
}
