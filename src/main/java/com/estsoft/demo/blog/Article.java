package com.estsoft.demo.blog;

import com.estsoft.demo.blog.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder // 빌더 패턴 밑 모든 코드를 lombok으로 작성할 수 있음
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toDTO() {
        return new ArticleResponse(id, title, content);
    }

    // 빌더 패턴
//    public Article(Builder builder) {
//        this.title = builder.title;
//        this.content = builder.content;
//    }
//
//    public static class Builder {
//        private String title;
//        private String content;
//
//        Builder title(String title) {
//            this.title = title;
//            return this;
//        }
//
//        Builder content(String content) {
//            this.content = content;
//            return this;
//        }
//
//        Article build() {
//            return new Article(this);
//        }
//    }
}
