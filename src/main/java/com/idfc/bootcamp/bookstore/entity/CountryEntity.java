package com.idfc.bootcamp.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
