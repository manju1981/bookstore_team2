package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.service.BookService;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@Validated @RequestBody BookDto dto) {
        return ResponseEntity.ok(MapperUtility.convertClass(bookService.create(dto), BookDto.class));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<BookDto> fetchBook(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(MapperUtility.convertClass(bookService.findById(id), BookDto.class));
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<BookDto>> findBooksPageable(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(MapperUtility.convertPageToList(bookService.findBooksPageable(page, size), BookDto.class));
    }
}
