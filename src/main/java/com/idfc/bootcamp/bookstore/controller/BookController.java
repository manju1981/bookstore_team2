package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.dto.PagedResponse;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.dto.QuantityDto;
import com.idfc.bootcamp.bookstore.exceptions.ApplicationException;
import com.idfc.bootcamp.bookstore.service.BookService;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto dto) throws Exception {
        return ResponseEntity.ok(MapperUtility.convertClass(bookService.create(dto), BookDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> fetchBook(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(MapperUtility.convertClass(bookService.findById(id), BookDto.class));
    }

    @CrossOrigin
    @GetMapping("/fetch-all")
    public ResponseEntity<PagedResponse<BookDto>> findBooksPageable(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sort,
            @RequestParam(defaultValue = "false") Boolean descending) {
        return ResponseEntity.ok(MapperUtility.convertPageToList(bookService.findBooksPageable(search,page, size, sort,descending), BookDto.class));
    }


    @PostMapping("/update-quantity/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(value = "id") Long id, @RequestBody @Valid QuantityDto dto) throws Exception {
        return ResponseEntity.ok(MapperUtility.convertClass(bookService.update(id, dto), BookDto.class));
    }
}
