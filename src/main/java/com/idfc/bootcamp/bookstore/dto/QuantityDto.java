package com.idfc.bootcamp.bookstore.dto;

import com.idfc.bootcamp.bookstore.enums.Type;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class QuantityDto {
    @Min(1)
    private int quantity;
    private Type type;
}
