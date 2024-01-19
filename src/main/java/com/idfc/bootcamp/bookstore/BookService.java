package com.idfc.bootcamp.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> fetchAllBooks(){
//        Book b1 = new Book("abc");
//        Book b2 = new Book("xyz");
        return bookRepository.findAll();
    }
}
