package com.idfc.bootcamp.bookstore.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Valid
public class CountryDto {

    private Long id;
    @NotEmpty(message = "name cannot be Empty and NULL")
    private String name;
}
