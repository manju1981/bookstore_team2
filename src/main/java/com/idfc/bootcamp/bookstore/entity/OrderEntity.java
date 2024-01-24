package com.idfc.bootcamp.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "book_id")
    private String bookId;

    private int quantity;

    @Column(name = "total_order_values")
    private double totalOrderValues;
}
