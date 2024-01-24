package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.dto.QuantityDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.enums.Type;
import com.idfc.bootcamp.bookstore.exceptions.ApiErrors;
import com.idfc.bootcamp.bookstore.exceptions.ApplicationException;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public BookEntity create(BookDto dto) {
        if (Objects.nonNull(dto.getId()) && bookRepository.findById(dto.getId()).isPresent()) {
            throw new ApplicationException(
                    ApiErrors.BOOK_ALREADY_EXIST, dto.getId()
            );
        }
        setBookId(dto);
        return bookRepository.save(Objects.requireNonNull(MapperUtility.convertClass(dto, BookEntity.class)));
    }

    private void setBookId(BookDto dto) {
        String generatedIsbn = generateIsbn();
        dto.setBookId(generatedIsbn);
    }

    private String generateIsbn() {
        return RandomStringUtils.randomAlphanumeric(5) + "-" + RandomStringUtils.randomAlphanumeric(5);
    }

    public BookEntity findById(Long id) {

        return MapperUtility.convertClass(bookRepository.findById(id), BookEntity.class);
    }

    public Page<BookEntity> findBooksPageable(String data, int page, int offset, String sort, boolean order) {
        if (page - 1 < 0) {
            throw new ApplicationException(ApiErrors.INVALID_PAGE, page);
        }
        if (order) {
            return bookRepository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase(data, data, data, PageRequest.of(page - 1, offset, Sort.by(sort).descending()));
        }
        return bookRepository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase(data, data, data, PageRequest.of(page - 1, offset, Sort.by(sort)));
    }
    public BookEntity update(Long id, QuantityDto book) {
        Optional<BookEntity> existingBook = bookRepository.findById(id);
        if (existingBook.isEmpty() || book.getQuantity() == 0) {
            throw new ApplicationException(ApiErrors.BOOK_NOT_FOUND, id);
        }
        if (Type.ADD.equals(book.getType())) {
            existingBook.get().setQuantity(book.getQuantity() + existingBook.get().getQuantity());
        } else if (existingBook.get().getQuantity() - book.getQuantity() >= 0) {
            existingBook.get().setQuantity(existingBook.get().getQuantity() - book.getQuantity());
        }
        bookRepository.save(existingBook.get());
        return existingBook.get();
    }

    public List<BookEntity> findByField(String fieldName, String fieldValue) {
        List<BookEntity> byField = bookRepository.findByField(fieldName, fieldValue);
        if (byField.isEmpty()) {
            return Collections.emptyList();
        }
        return byField;
    }
}
