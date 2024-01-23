package com.idfc.bootcamp.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagedResponse<T> {
    private List<T> content;
    private int currentPage;
    private int offset;
    private int count;
    private long totalElements;
    private int totalPages;
}