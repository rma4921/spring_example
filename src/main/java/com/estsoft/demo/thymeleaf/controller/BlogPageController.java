package com.estsoft.demo.thymeleaf.controller;

import com.estsoft.demo.blog.Article;
import com.estsoft.demo.blog.service.BlogService;
import com.estsoft.demo.thymeleaf.dto.ArticleViewResponse;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {
    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleViewResponse> articleList = blogService.findAllArticles()
                .stream().map(ArticleViewResponse::new)
                .toList();

        model.addAttribute("articles", articleList);
        return "articleList";
    }

    // 게시글 단 건 조회
    @GetMapping("articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = blogService.findArticleById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    // /new-article -> newArticle.html (생성 / 수정)
    // /new-article?id=1 -> @RequestParam으로
    // /new-article/{id} -> @PathVariable으로
    @GetMapping("/new-article")
    public String showBlogEditPage(@RequestParam(required = false) Long id, Model model) { // (required = false) 꼭 필요하지 X
        if (id == null) { // 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else { // 수정
            Article article = blogService.findArticleById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
