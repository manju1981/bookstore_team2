package com.idfc.bootcamp.bookstore.entity;

import com.idfc.bootcamp.bookstore.dto.QuantityDto;
import com.idfc.bootcamp.bookstore.exceptions.ApiErrors;
import com.idfc.bootcamp.bookstore.exceptions.ApplicationException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "book_id")
    private String bookId;
    private String author;
    private String description;
    private int rating;
    private String img;
    private double price;
    private int quantity;

    public void changeQuantity(QuantityDto quantityDto) {
        switch (quantityDto.getType()) {
            case ADD:
                this.quantity += quantityDto.getQuantity();
                break;
            case REMOVE:
                if (hasSufficientQuantity(quantityDto.getQuantity())) {
                    this.quantity -= quantityDto.getQuantity();
                } else {
                    throw new ApplicationException(ApiErrors.INSUFFICIENT_QUANTITY);
                }
                break;
            default:
                throw new ApplicationException(ApiErrors.INVALID_OPERATION);
        }
    }

    public boolean hasSufficientQuantity(int quantity) {
        return this.quantity - quantity >= 0;
    }
}
