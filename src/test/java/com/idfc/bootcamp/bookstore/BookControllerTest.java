package com.idfc.bootcamp.bookstore;


import static org.hamcrest.CoreMatchers.is;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.dto.QuantityDto;
import com.idfc.bootcamp.bookstore.dto.SearchCriteria;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.enums.Type;
import com.idfc.bootcamp.bookstore.exceptions.ApiErrors;
import com.idfc.bootcamp.bookstore.exceptions.ApplicationException;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.repository.CountryRepository;
import com.idfc.bootcamp.bookstore.repository.OrderRepository;
import com.idfc.bootcamp.bookstore.service.OrderService;
import org.junit.jupiter.api.AfterEach;
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

import java.awt.print.Book;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    CountryRepository countryRepository;
    @MockBean
    OrderService orderService;
    @MockBean
    OrderRepository orderRepository;

    @Test
    @DisplayName("should return success http status")
    void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/api/v1/books/fetch-all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should return the books in descending order")
    void shouldReturnTheBooksInDescendingOrder() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code1","test",  "Robert Cecil","desc",0,"image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code2", "test", "Robert Cecil","desc",1,"image",20.00,1);
        Page<BookEntity> pagedTasks = new PageImpl(List.of(b2,b1));
        when(bookRepository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase("","","",PageRequest.of(0, 10,Sort.by("rating").descending()))).thenReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/books/fetch-all?page=1&size=10&sort=rating&descending=true")).
                andExpect(MockMvcResultMatchers.jsonPath("content[0].rating").value(1)).
                andExpect(MockMvcResultMatchers.jsonPath("content[1].rating").value(0));
    }

    @Test
    @DisplayName("should return six books")
    void shouldReturnSixBooks() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code","test", "Robert Cecil","desc",1,"image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code", "test","Robert Cecil","desc",1,"image",20.00,1);
        BookEntity b3 = new BookEntity(1L,"Clean Code","test", "Robert Cecil","desc",1,"image",20.00,1);
        BookEntity b4 = new BookEntity(2L,"Clean Code", "test","Robert Cecil","desc",1,"image",20.00,1);
        BookEntity b5 = new BookEntity(1L,"Clean Code","test", "Robert Cecil","desc",1,"image",20.00,1);
        BookEntity b6 = new BookEntity(2L,"Clean Code", "test","Robert Cecil","desc",1,"image",20.00,1);
        Page<BookEntity> pagedTasks = new PageImpl(List.of(b1,b2,b1,b2,b1,b2));
        when(bookRepository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase("","","",PageRequest.of(0, 10, Sort.by("title")))).thenReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/books/fetch-all")).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6));
    }

    @Test
    public void testBookAlreadyExists() throws Exception {
        // Arrange
        Long existingBookId = 1L;
        BookDto dto = new BookDto();
        dto.setId(existingBookId);
        BookEntity b1 = new BookEntity(1L,"Clean Code","test", "Robert Cecil","desc",1,"image",20.00,1);


        // Mock the behavior of bookRepository.findById to return a non-null value
        when(bookRepository.findById(existingBookId)).thenReturn(Optional.of(b1));

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(b1)))
                .andExpect(status().is4xxClientError());
        // Verify that bookRepository.findById was called with the correct ID
        verify(bookRepository, times(1)).findById(existingBookId);
    }

    @Test
    @DisplayName("should return the first book titles")
    void shouldReturnTheFirstBookTitleAsAbc() throws Exception {
        BookEntity b1 = new BookEntity(1L,"Clean Code1","test", "Robert Cecil","desc",0,"image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code2", "test","Robert Cecil","desc",1,"image",20.00,1);
        Page<BookEntity> pagedTasks = new PageImpl(List.of(b1,b2));
        when(bookRepository.findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCaseOrDescriptionContainsIgnoreCase("","","",PageRequest.of(0, 10,Sort.by("rating")))).thenReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/books/fetch-all?page=1&size=10&sort=rating&descending=false")).
                andExpect(MockMvcResultMatchers.jsonPath("content[0].title").value("Clean Code1")).
                andExpect(MockMvcResultMatchers.jsonPath("content[1].title").value("Clean Code2"));
    }

    @Test
    @DisplayName("should create a book")
    void shouldCreateBook() throws Exception {
        BookEntity book = new BookEntity(5L, "New Book","test", "John Doe", "Description",1, "Image", 29.99,1);
        when(bookRepository.save(any())).thenReturn(book);

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should fetch a book by id")
    void shouldFetchBookById() throws Exception {
        long bookId = 1L;
        BookEntity book = new BookEntity(bookId, "Fetched Book","test", "Jane Doe", "Description", 1,"Image", 39.99,1);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/v1/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Fetched Book"))
                .andExpect(jsonPath("$.author").value("Jane Doe"));

        verify(bookRepository).findById(bookId);
    }

    @Test
    @DisplayName("should update the existing book and its quantity")
    void shouldUpdateTheExistingBookAndItsQuantity() throws Exception {
        // Given
        BookEntity existingBook = new BookEntity(5L, "New Book","test", "John Doe", "Description", 1,"Image", 29.99, 1);
        when(bookRepository.findById(5L)).thenReturn(Optional.of(existingBook));

        QuantityDto quantity = new QuantityDto(5, Type.ADD);
        // When
        mockMvc.perform(post("/api/v1/books/update-quantity/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quantity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(6));
    }

    @Test
    @DisplayName("should update the existing book and its quantity and sell")
    void shouldUpdateTheExistingBookAndItsQuantitySell() throws Exception {
        // Given
        BookEntity existingBook = new BookEntity(5L, "New Book","test", "John Doe", "Description", 1,"Image", 29.99, 15);
        when(bookRepository.findById(5L)).thenReturn(Optional.of(existingBook));

        QuantityDto quantity = new QuantityDto(5, Type.REMOVE);
        // When
        mockMvc.perform(post("/api/v1/books/update-quantity/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quantity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(10));
    }


    @Test
    @DisplayName("should not create a book when validation Error")
    void shouldNotCreateABookWhenValidationError() throws Exception {
        BookEntity book = new BookEntity(5L, "New Book","test",  "", "Description",1, "Image", 29.99,1);
        Map<String,String> response=new HashMap<String,String>();
        response.put("author", "author should not be empty");

        when(bookRepository.save(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("test find by field")
    public void testFindByField() throws Exception {
        // Arrange
        String fieldName = "title";
        String fieldValue = "Harry";
        BookEntity book = new BookEntity();
        book.setTitle("Harry Potter and the Philosopher's Stone");
        List<BookEntity> expectedBooks = Arrays.asList(book);
        when(bookRepository.findByField(fieldName, fieldValue)).thenReturn(expectedBooks);

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFieldName(fieldName);
        searchCriteria.setFieldValue(fieldValue);

        ObjectMapper mapper = new ObjectMapper();
        String jsonSearchCriteria = mapper.writeValueAsString(searchCriteria);

        // Act and Assert
        mockMvc.perform(post("/api/v1/books/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSearchCriteria))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(book.getTitle())));
    }

    @Test
    @DisplayName("test find by field not found")
    public void testFindByFieldIfNotFound() throws Exception {
        // Arrange
        String fieldName = "title";
        String fieldValue = "test";
        when(bookRepository.findByField(fieldName, fieldValue)).thenReturn(new ArrayList<>());

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFieldName(fieldName);
        searchCriteria.setFieldValue(fieldValue);

        ObjectMapper mapper = new ObjectMapper();
        String jsonSearchCriteria = mapper.writeValueAsString(searchCriteria);

        // Act and Assert
        mockMvc.perform(post("/api/v1/books/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSearchCriteria))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("should return error when page number is in valid")
    void shouldReturnErrorWhenPageNumberIsInvalid() throws Exception {
        mockMvc.perform(get("/api/v1/books/fetch-all?page=-1&size=10&sort=rating&descending=false")).
                andExpect(status().is4xxClientError());
    }



    @Test
    @DisplayName("should show error when quantity is o")
    void shouldThrowErrorWhenQuantityIs0() throws Exception {
        QuantityDto quantity = new QuantityDto(1, Type.REMOVE);
        // When
        mockMvc.perform(post("/api/v1/books/update-quantity/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quantity)))
                .andExpect(status().is4xxClientError());
    }


    @AfterEach
    @DisplayName("delete books")
    void deleteBooks() throws Exception {
        bookRepository.deleteAll();
    }



}