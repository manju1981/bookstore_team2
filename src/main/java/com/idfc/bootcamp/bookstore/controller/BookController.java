package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.service.BookService;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    /*@GetMapping("/find-all")
    public ResponseEntity<List<BookDto>> listBooks() {
        return ResponseEntity.ok(MapperUtility.mapList(bookService.fetchAllBooks(), BookDto.class));
    }*/
    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto dto) {
        return ResponseEntity.ok(MapperUtility.convertClass(bookService.create(dto), BookDto.class));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<BookDto> fetchBook(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(MapperUtility.convertClass(bookService.findById(id), BookDto.class));
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<BookDto>> findBooksPageable(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(MapperUtility.convertPageToList(bookService.findBooksPageable(page, size), BookDto.class));
    }
}
