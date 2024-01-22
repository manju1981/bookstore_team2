package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> fetchAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity create(BookDto dto) {
        return bookRepository.save(Objects.requireNonNull(MapperUtility.convertClass(dto, BookEntity.class)));
    }

    public BookEntity findById(Long id) {
        return MapperUtility.convertClass(bookRepository.findById(id), BookEntity.class);
    }

    public Page<BookEntity> findBooksPageable(int page, int offset) {
        return bookRepository.findAll(PageRequest.of(page - 1, offset));
    }

    public BookEntity update(Long id, BookDto book) {
        Optional<BookEntity> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent() && book.getQuantity() != 0) {
            existingBook.get().setQuantity(book.getQuantity() + existingBook.get().getQuantity());
            bookRepository.save(existingBook.get());
            return existingBook.get();
        }
        return new BookEntity();
    }

    public List<BookEntity> searchBooks(String data){
        return bookRepository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase(data,data,data);
    }
}
