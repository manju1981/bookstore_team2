package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.dto.CountryDto;
import com.idfc.bootcamp.bookstore.entity.CountryEntity;
import com.idfc.bootcamp.bookstore.service.CountryService;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/country")
public class CountryController {

    CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/fetch-all")
    public List<CountryEntity> findCountries() {
        return countryService.fetchAllCountries();
    }

    @PostMapping("/create")
    public ResponseEntity<CountryDto> createBook(@Valid @RequestBody CountryDto dto) {
        return ResponseEntity.ok(MapperUtility.convertClass(countryService.create(dto), CountryDto.class));
    }
}
