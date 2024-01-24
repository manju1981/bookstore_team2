package com.idfc.bootcamp.bookstore;


import com.idfc.bootcamp.bookstore.dto.BookDto;
import com.idfc.bootcamp.bookstore.dto.CountryDto;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.entity.CountryEntity;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.repository.CountryRepository;
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
public class CountryApiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CountryRepository countryRepository;

    @LocalServerPort
    int randomServerPort;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + randomServerPort + "/api/v1/";
    }

    @Test
    @DisplayName("should return list of countries when endpoint is accessed")
    void shouldReturnListOfCountriesWhenEndpointIsAccessed() {
        CountryEntity country1 = new CountryEntity(5L,"India");
        CountryEntity country2 = new CountryEntity(6L,"SriLanka");
        countryRepository.deleteAll();
        countryRepository.saveAll(Arrays.asList(country1,country2));
        final List<CountryDto> countries = restTemplate.exchange(baseUrl + "/countries/fetch-all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CountryDto>>() {
                }).getBody();
        assertEquals(2, countries.size());
    }

    @Test
    @DisplayName("should create a country and return the created book")
    void shouldCreateBookAndReturnCreatedBook() {
        CountryDto countryDto = new CountryDto(5L,"India");

        ResponseEntity<CountryDto> response = restTemplate.postForEntity(baseUrl + "/countries", countryDto, CountryDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("India", response.getBody().getName());
    }


}
