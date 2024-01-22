package com.idfc.bootcamp.bookstore;

import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.flyway.enabled=false"})
public class BookApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @LocalServerPort
    int randomServerPort;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + randomServerPort + "/api/v1/";
    }

    @Test
    @DisplayName("should return list of books when endpoint is accessed")
    void shouldReturnListOfBooksWhenEndpointIsAccessed() {
        BookEntity b1 = new BookEntity(1L,"Clean Code", "Robert Cecil","desc",1,"image",20.00,1);
        BookEntity b2 = new BookEntity(2L,"Clean Code", "Robert Cecil","desc",1,"image",20.00,1);
        bookRepository.deleteAll();
        bookRepository.saveAll(Arrays.asList(b1, b2));
        final List<Book> books = restTemplate.exchange(baseUrl + "/book/fetch-all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
                }).getBody();
        assertEquals(2, books.size());
    }

    @Test
    @DisplayName("should create a book and return the created book")
    void shouldCreateBookAndReturnCreatedBook() {
        BookDto bookDto = new BookDto(5L,"Clean Code", "Robert Cecil", "desc",1, "image", 20.00,1);

        ResponseEntity<BookDto> response = restTemplate.postForEntity(baseUrl + "/book/create", bookDto, BookDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Clean Code", response.getBody().getTitle());
        // Add more assertions based on the expected values.
    }

    @Test
    @DisplayName("should fetch a book by Id")
    void shouldFetchBookById() {
        // Assuming there is a book with ID 1 in the database
        Long existingBookId = 1L;
        BookEntity b1 = new BookEntity(1L, "Clean Code", "Robert Cecil", "desc",1, "image", 20.00,1);
        bookRepository.save(b1);
        ResponseEntity<BookDto> response = restTemplate.exchange(
                baseUrl + "book/fetch/{id}", HttpMethod.GET, null,
                new ParameterizedTypeReference<BookDto>() {}, existingBookId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        /*assertNotNull(response.getBody());
        assertEquals(existingBookId, response.getBody().getId());*/
    }

    @Test
    @DisplayName("should fetch list of books based on search ")
    void shouldFetchListOfBooksBasedOnSearch() {
        BookEntity b1 = new BookEntity(1L, "Clean Code", "Robert Cecil", "desc",1, "image", 20.00,1);
        BookEntity b2 = new BookEntity(1L, "Clean Code", "search Cecil", "desc",1, "image",20.00,1);
        BookEntity b3 = new BookEntity(1L, "Clean Code", "search Cecil", "desc",1, "image", 20.00,1);
        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);
        final List<Book> books = restTemplate.exchange(baseUrl + "/book/search?data=search", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
                }).getBody();
        assertEquals(2, books.size());
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }
}
