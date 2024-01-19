package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.service.BookService;
import com.idfc.bootcamp.bookstore.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books")
    public List<Book> listBooks(){
        return bookService.fetchAllBooks();
    }
}
