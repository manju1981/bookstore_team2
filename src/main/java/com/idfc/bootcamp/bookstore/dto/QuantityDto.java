package com.idfc.bootcamp.bookstore.dto;

import com.idfc.bootcamp.bookstore.enums.Type;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QuantityDto {
    private int quantity;
    private Type type;
}
