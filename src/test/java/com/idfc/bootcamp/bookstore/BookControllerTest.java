package com.idfc.bootcamp.bookstore;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfc.bootcamp.bookstore.dto.QuantityDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.enums.Type;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.repository.CountryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @MockBean
    CountryRepository countryRepository;

    @Test
    @DisplayName("should return success http status")
    void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/api/v1/book/fetch-all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should return the books in descending order")
    void shouldReturnTheBooksInDescendingOrder() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code1", "Robert Cecil","desc",0,"image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code2", "Robert Cecil","desc",1,"image",20.00,1);
        Page<BookEntity> pagedTasks = new PageImpl(List.of(b2,b1));
        when(bookRepository.findAll(PageRequest.of(0, 10,Sort.by("rating").descending()))).thenReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/book/fetch-all?page=1&size=10&sort=rating&descending=true")).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].rating").value(1)).
                andExpect(MockMvcResultMatchers.jsonPath("$[1].rating").value(0));
    }

    @Test
    @DisplayName("should return two books")
    void shouldReturnTwoBooks() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code", "Robert Cecil","desc",1,"image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code", "Robert Cecil","desc",1,"image",20.00,1);
        Page<BookEntity> pagedTasks = new PageImpl(List.of(b1,b2));
        when(bookRepository.findAll(PageRequest.of(0, 10, Sort.by("title")))).thenReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/book/fetch-all")).andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("should return the first book titles")
    void shouldReturnTheFirstBookTitleAsAbc() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code1", "Robert Cecil","desc",0,"image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code2", "Robert Cecil","desc",1,"image",20.00,1);
        Page<BookEntity> pagedTasks = new PageImpl(List.of(b1,b2));
        when(bookRepository.findAll(PageRequest.of(0, 10,Sort.by("rating")))).thenReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/book/fetch-all?page=1&size=10&sort=rating&descending=false")).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Clean Code1")).
                andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Clean Code2"));
    }

    @Test
    @DisplayName("should create a book")
    void shouldCreateBook() throws Exception {
        BookEntity book = new BookEntity(5L, "New Book", "John Doe", "Description",1, "Image", 29.99,1);
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
        BookEntity book = new BookEntity(bookId, "Fetched Book", "Jane Doe", "Description", 1,"Image", 39.99,1);
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
        BookEntity existingBook = new BookEntity(5L, "New Book", "John Doe", "Description", 1,"Image", 29.99, 1);
        when(bookRepository.findById(5L)).thenReturn(Optional.of(existingBook));

        QuantityDto quantity = new QuantityDto(5, Type.ADD);
        // When
        mockMvc.perform(post("/api/v1/book/update-quantity/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quantity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(6));
    }

    @Test
    @DisplayName("should update the existing book and its quantity and sell")
    void shouldUpdateTheExistingBookAndItsQuantitySell() throws Exception {
        // Given
        BookEntity existingBook = new BookEntity(5L, "New Book", "John Doe", "Description", 1,"Image", 29.99, 15);
        when(bookRepository.findById(5L)).thenReturn(Optional.of(existingBook));

        QuantityDto quantity = new QuantityDto(5, Type.REMOVE);
        // When
        mockMvc.perform(post("/api/v1/book/update-quantity/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quantity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    @DisplayName("should return two books which matches the search")
    void shouldReturnTwoBooksWhichMatchesTheSearch() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code", "Robert Cecil","desc",1,"image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code", "Robert Cecil","desc",1,"image",20.00,1);
        List<BookEntity> books=List.of(b1,b2);
        String data="cle";
        when(bookRepository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase("c","c","c")).thenReturn(books);
        mockMvc.perform(get("/api/v1/book/search?data=c")).andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("should not create a book when validation Error")
    void shouldNotCreateABookWhenValidationError() throws Exception {
        BookEntity book = new BookEntity(5L, "New Book", "", "Description",1, "Image", 29.99,1);
        Map<String,String> response=new HashMap<String,String>();
        response.put("author", "author should not be empty");

        when(bookRepository.save(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().is4xxClientError());
    }


}
