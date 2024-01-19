package com.idfc.bootcamp.bookstore;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

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

    @Test
    @DisplayName("should return success http status")
    void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/book/find-all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should return two books")
    void shouldReturnTwoBooks() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code", "Robert Cecil","desc","image",20.00);
        BookEntity b2 = new BookEntity(2L,"Clean Code", "Robert Cecil","desc","image",20.00);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1,b2));
        mockMvc.perform(get("/book/find-all")).andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("should return the first book titles")
    void shouldReturnTheFirstBookTitleAsAbc() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code1", "Robert Cecil","desc","image",20.00);
        BookEntity b2 = new BookEntity(2L,"Clean Code2", "Robert Cecil","desc","image",20.00);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1,b2));
        mockMvc.perform(get("/book/find-all")).
                andExpect(jsonPath("$[0].title").value("Clean Code1")).
                andExpect(jsonPath("$[1].title").value("Clean Code2"));
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("should create a book")
    void shouldCreateBook() throws Exception {
        BookEntity book = new BookEntity(5L, "New Book", "John Doe", "Description", "Image", 29.99);
        when(bookRepository.save(Mockito.any())).thenReturn(book);

        mockMvc.perform(post("/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("John Doe"));

    }

    @Test
    @DisplayName("should fetch a book by id")
    void shouldFetchBookById() throws Exception {
        long bookId = 1L;
        BookEntity book = new BookEntity(bookId, "Fetched Book", "Jane Doe", "Description", "Image", 39.99);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/book/fetch/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Fetched Book"))
                .andExpect(jsonPath("$.author").value("Jane Doe"));

        verify(bookRepository).findById(bookId);
    }

}
