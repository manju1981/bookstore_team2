package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> fetchAllBooks(){
        return bookRepository.findAll();
    }

    public BookEntity create(BookDto dto) {
        return bookRepository.save(MapperUtility.convertClass(dto, BookEntity.class));
    }

    public BookEntity findById(Long id) {
        return MapperUtility.convertClass(bookRepository.findById(id), BookEntity.class);
    }

    public Page<BookEntity> findBooksPageable(int page, int offset) {
        return bookRepository.findAll(PageRequest.of(page - 1, offset));
    }
}
