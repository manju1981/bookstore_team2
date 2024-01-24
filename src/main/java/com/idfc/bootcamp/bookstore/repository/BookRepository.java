package com.idfc.bootcamp.bookstore.repository;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Page<BookEntity> findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String author,String description,Pageable pageable);
    BookEntity findByIdAndAuthor(Long id, String author);
    @Query(value = "SELECT * FROM books b WHERE " +
            "(LOWER(COALESCE(b.title, '')) LIKE LOWER(:fieldValue) || '%' AND :fieldName = 'title') OR " +
            "(LOWER(COALESCE(b.author, '')) LIKE LOWER(:fieldValue) || '%' AND :fieldName = 'author') OR " +
            "(LOWER(COALESCE(b.description, '')) LIKE LOWER(:fieldValue) || '%' AND :fieldName = 'description') OR " +
            "(LOWER(COALESCE(b.book_id, '')) LIKE LOWER(:fieldValue) || '%' AND :fieldName = 'bookId') OR " +
            "(LOWER(COALESCE(CAST(b.rating AS VARCHAR), '')) LIKE LOWER(:fieldValue) || '%' AND :fieldName = 'rating') OR " +
            "(LOWER(COALESCE(b.img, '')) LIKE LOWER(:fieldValue) || '%' AND :fieldName = 'img') OR " +
            "(LOWER(COALESCE(CAST(b.price AS VARCHAR), '')) LIKE LOWER(:fieldValue) || '%' AND :fieldName = 'price') OR " +
            "(LOWER(COALESCE(CAST(b.quantity AS VARCHAR), '')) LIKE LOWER(:fieldValue) || '%' AND :fieldName = 'quantity')", nativeQuery = true)
    List<BookEntity> findByField(@Param("fieldName") String fieldName,@Param("fieldValue") String fieldValue);
}
