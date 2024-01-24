package com.idfc.bootcamp.bookstore.dto.order_request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderRequest {
    private Long orderId;
    @NotNull
    private Long countryId;
    private List<OrderItem> orders;
    private double totalOrderValues;
}