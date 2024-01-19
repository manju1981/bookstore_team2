package com.idfc.bootcamp.bookstore.repository;

import com.idfc.bootcamp.bookstore.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> { }
