package com.estsoft.demo.book.service;

import com.estsoft.demo.book.domain.Book;
import com.estsoft.demo.book.dto.AddBookRequest;
import com.estsoft.demo.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("no exists id: " + id));
    }

    public Book saveBook(AddBookRequest request) {
        Book book = request.toEntity();
        return bookRepository.save(book);
    }
}
