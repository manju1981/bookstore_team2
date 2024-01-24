package com.idfc.bootcamp.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchCriteria {
    @NotEmpty
    private String fieldName;
    @NotEmpty
    private String fieldValue;
}