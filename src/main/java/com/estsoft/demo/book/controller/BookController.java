package com.estsoft.demo.book.controller;

import com.estsoft.demo.book.dto.AddBookRequest;
import com.estsoft.demo.book.dto.BookViewResponse;
import com.estsoft.demo.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 전부 조회
    @GetMapping("/books")
    public String findAllBooks(Model model) {
        List<BookViewResponse> bookList = bookService.findAllBook()
                .stream()
                .map(BookViewResponse::new)
                .toList();
        model.addAttribute("bookList", bookList);

        return "bookManage";
    }

    // 단 건 조회 (id로)
    // RequestMapping(method = RequestMethod.Get) == @GetMapping()
    @GetMapping("/books/{id}")
    public String findBookById(@PathVariable("id") String id, Model model) {
        BookViewResponse book = new BookViewResponse(bookService.getBookById(id));
        model.addAttribute("book", book);

        return "bookDetail";
    }

    // 단 건 저장
    @PostMapping("/books")
    public String addBook(@ModelAttribute("book") AddBookRequest request) { // @ModelAttribute : 객체 단위로 바인딩
        bookService.saveBook(request);
        return "redirect:/books";
    }
}
