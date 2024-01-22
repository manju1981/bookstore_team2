package com.idfc.bootcamp.bookstore.repository;

import com.idfc.bootcamp.bookstore.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}
