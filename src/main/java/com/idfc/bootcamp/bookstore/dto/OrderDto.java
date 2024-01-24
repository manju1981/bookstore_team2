package com.idfc.bootcamp.bookstore.dto;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
public class OrderDto {
    private Long id;
    private Long orderId;
    private Long countryId;
    private String bookId;
    private int quantity;
    private double totalOrderValues;
}
