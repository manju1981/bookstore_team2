package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.dto.CountryDto;
import com.idfc.bootcamp.bookstore.entity.CountryEntity;
import com.idfc.bootcamp.bookstore.repository.CountryRepository;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CountryService {
    CountryRepository countryRepository;
    @Autowired
    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public List<CountryEntity> fetchAllCountries(){
        return countryRepository.findAll();
    }
    public CountryEntity create(CountryDto dto) {
        return countryRepository.save(Objects.requireNonNull(MapperUtility.convertClass(dto, CountryEntity.class)));
    }
}
