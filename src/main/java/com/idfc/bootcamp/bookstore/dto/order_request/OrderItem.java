package com.idfc.bootcamp.bookstore.dto.order_request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderItem {
    @NotNull
    private String bookId;
    private int quantity;
    @JsonIgnore
    private double price;
}