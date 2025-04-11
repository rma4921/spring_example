package com.estsoft.demo.book.dto;

import com.estsoft.demo.book.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookViewResponse {
    private String id;
    private String name;
    private String author;

    public BookViewResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
    }
}
