package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.dto.QuantityDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.exceptions.ApiErrors;
import com.idfc.bootcamp.bookstore.exceptions.ApplicationException;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        return bookRepository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase(data, data, data, PageRequest.of(page - 1, offset, order ? Sort.by(sort).descending() : Sort.by(sort)));
    }

    public BookEntity update(Long id, QuantityDto book) {
        BookEntity existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ApiErrors.BOOK_NOT_FOUND, id));
        if (book.getQuantity() == 0) throw new ApplicationException(ApiErrors.INVALID_QUANTITY);
        existingBook.changeQuantity(book);
        bookRepository.save(existingBook);
        return existingBook;
    }

    public List<BookEntity> findByField(String fieldName, String fieldValue) {
        return bookRepository.findByField(fieldName, fieldValue);
    }

    public BookEntity findByBookId(String bookId) {
        return MapperUtility.convertClass(bookRepository.findByBookId(bookId), BookEntity.class);
    }
}
