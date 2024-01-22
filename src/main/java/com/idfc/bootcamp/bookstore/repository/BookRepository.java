package com.idfc.bootcamp.bookstore.repository;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String author,String description);

    Page<BookEntity> findAll(Pageable pageable);
    BookEntity findByIdAndAuthor(Long id, String author);
}
