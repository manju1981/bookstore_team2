package com.idfc.bootcamp.bookstore.dto;

//import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {

    private Long id;
    @NotEmpty(message = "title not be Empty and null")
    private String title;
    @NotEmpty(message = "author should not be empty")
    private String author;
    @NotEmpty(message = "description should not be empty")
    private String description;
    @NotEmpty(message = "img should not be empty")
    private String img;
    private double price;
}