package com.idfc.bootcamp.bookstore.dto.order_request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(hidden = true)
    private Long orderId;
    @NotNull
    private Long countryId;
    private List<OrderItem> orders;
    @Schema(hidden = true)
    private double totalOrderValues;
}