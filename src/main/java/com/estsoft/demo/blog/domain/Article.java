package com.estsoft.demo.blog.domain;

import com.estsoft.demo.blog.dto.ArticleResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 엔티티의 변화를 감지하여 엔티티와 매핑된 테이블의 데이터를 조작.
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @JsonProperty("body")
    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder // 빌더 패턴 밑 모든 코드를 lombok으로 작성할 수 있음
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toDTO() {
        return new ArticleResponse(id, title, content);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
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
