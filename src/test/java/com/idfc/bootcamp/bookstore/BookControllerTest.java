package com.idfc.bootcamp.bookstore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should return two books")
    void shouldReturnTwoBooks() throws Exception {
        Book b1 = new Book("Clean Code", "Robert Cecil");
        Book b2 = new Book("Programming Pearls", "Jon Bently");
        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1,b2));
        mockMvc.perform(get("/books")).andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("should return the first book titles")
    void shouldReturnTheFirstBookTitleAsAbc() throws Exception {
        Book b1 = new Book("Clean Code", "Robert Cecil");
        Book b2 = new Book("Programming Pearls", "Jon Bently");
        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1,b2));
        mockMvc.perform(get("/books")).
                andExpect(jsonPath("$[0].title").value("abc")).
                andExpect(jsonPath("$[1].title").value("xyz"));
        verify(bookRepository).findAll();
    }
    
}
