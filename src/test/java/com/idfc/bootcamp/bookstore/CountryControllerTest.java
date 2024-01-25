package com.idfc.bootcamp.bookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfc.bootcamp.bookstore.entity.CountryEntity;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.repository.CountryRepository;
import com.idfc.bootcamp.bookstore.repository.OrderRepository;
import com.idfc.bootcamp.bookstore.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CountryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CountryRepository countryRepository;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    OrderService orderService;
    @MockBean
    OrderRepository orderRepository;

    @Test
    @DisplayName("should return success http status")
    void shouldReturnSuccessHttpStatus() throws Exception {
        mockMvc.perform(get("/api/v1/countries/fetch-all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("should create a country when create api is called with required params")
    void shouldCreateACountryWhenCreateApiIsCalledWithRequiredParams() throws Exception {
        CountryEntity country = new CountryEntity(5L,"India");
        when(countryRepository.save(Mockito.any())).thenReturn(country);
        mockMvc.perform(post("/api/v1/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(country)))
                .andExpect(status().isOk());
    }
    @Test
    void findByCountryId_WhenCountryExists_ShouldReturnOptionalWithCountryEntity() throws Exception {
        CountryEntity sampleCountryEntity = createSampleCountryEntity();
        when(countryRepository.findById(anyLong())).thenReturn(Optional.of(sampleCountryEntity));
        Optional<CountryEntity> result = countryRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(sampleCountryEntity, result.get());
        mockMvc.perform(post("/api/v1/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sampleCountryEntity)))
                .andExpect(status().isOk());
    }
    @Test
    void findByCountryId_WhenCountryDoesNotExist_ShouldReturnEmptyOptional() throws Exception {
        CountryEntity sampleCountryEntity = createSampleCountryEntity();
        when(countryRepository.findAll()).thenReturn(Collections.singletonList(sampleCountryEntity));
        mockMvc.perform(get("/api/v1/countries/fetch-all")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    private CountryEntity createSampleCountryEntity() {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1L);
        countryEntity.setName("INDIA");
        return countryEntity;
    }
}
