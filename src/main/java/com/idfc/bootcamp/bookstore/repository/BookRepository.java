package com.idfc.bootcamp.bookstore.repository;

import com.idfc.bootcamp.bookstore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> { }
