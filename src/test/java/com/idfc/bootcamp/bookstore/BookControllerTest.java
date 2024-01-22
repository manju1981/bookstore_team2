package com.idfc.bootcamp.bookstore;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.repository.CountryRepository;
import com.idfc.bootcamp.bookstore.service.BookService;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    CountryRepository countryRepository;

    @Test
    @DisplayName("should return success http status")
    void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/api/v1/book/fetch-all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should return two books")
    void shouldReturnTwoBooks() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code", "Robert Cecil","desc","image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code", "Robert Cecil","desc","image",20.00,1);
        Page<BookEntity> pagedTasks = new PageImpl(List.of(b1,b2));
        when(bookRepository.findAll(PageRequest.of(0, 10))).thenReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/book/fetch-all")).andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("should return the first book titles")
    void shouldReturnTheFirstBookTitleAsAbc() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code1", "Robert Cecil","desc","image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code2", "Robert Cecil","desc","image",20.00,1);
        Page<BookEntity> pagedTasks = new PageImpl(List.of(b1,b2));
        when(bookRepository.findAll(PageRequest.of(0, 10))).thenReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/book/fetch-all?page=1&size=10")).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Clean Code1")).
                andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Clean Code2"));
    }

    @Test
    @DisplayName("should create a book")
    void shouldCreateBook() throws Exception {
        BookEntity book = new BookEntity(5L, "New Book", "John Doe", "Description", "Image", 29.99,1);
        when(bookRepository.save(any())).thenReturn(book);

        mockMvc.perform(post("/api/v1/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should fetch a book by id")
    void shouldFetchBookById() throws Exception {
        long bookId = 1L;
        BookEntity book = new BookEntity(bookId, "Fetched Book", "Jane Doe", "Description", "Image", 39.99,1);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/v1/book/fetch/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Fetched Book"))
                .andExpect(jsonPath("$.author").value("Jane Doe"));

        verify(bookRepository).findById(bookId);
    }

    @Test
    @DisplayName("should update the existing book and its quantity")
    void shouldUpdateTheExistingBookAndItsQuantity() throws Exception {
        // Given
        BookEntity existingBook = new BookEntity(5L, "New Book", "John Doe", "Description", "Image", 29.99, 1);
        when(bookRepository.findById(5L)).thenReturn(Optional.of(existingBook));

        BookEntity updatedBook = new BookEntity(0L, null, null, null, null, 29.99, 1);
        // When
        mockMvc.perform(post("/api/v1/book/update/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(2));
    }

}
