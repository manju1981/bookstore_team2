package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.dto.QuantityDto;
import com.idfc.bootcamp.bookstore.service.BookService;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto dto) throws Exception {
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

    @GetMapping("/search")
    public ResponseEntity<List<BookEntity>> searchBooks(
            @RequestParam() String data){
        return ResponseEntity.ok(bookService.searchBooks(data));
    }



    @PostMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(value = "id") Long id, @RequestBody QuantityDto dto) throws Exception {
        return ResponseEntity.ok(MapperUtility.convertClass(bookService.update(id,dto), BookDto.class));
    }
}
