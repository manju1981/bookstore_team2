package com.idfc.bootcamp.bookstore.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {

    @Schema(hidden = true)
    private Long id;
    @NotEmpty(message = "title not be Empty and null")
    private String title;
    @Schema(hidden = true)
    private String bookId;
    @NotEmpty(message = "author should not be empty")
    private String author;
    @NotEmpty(message = "description should not be empty")
    private String description;
    private int rating;
    @NotEmpty(message = "img should not be empty")
    private String img;
    private double price;
    @Min(value = 1, message = "kindly add quantity of books")
    private int quantity;
}