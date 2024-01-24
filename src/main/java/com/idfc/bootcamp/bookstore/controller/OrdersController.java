package com.idfc.bootcamp.bookstore.controller;

import com.idfc.bootcamp.bookstore.dto.order_request.OrderRequest;
import com.idfc.bootcamp.bookstore.service.OrderService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrdersController {

    OrderService orderService;

    @Autowired
    public OrdersController(OrderService bookService) {
        this.orderService = bookService;
    }
    @PostMapping
    public ResponseEntity<OrderRequest> createBook(@Valid @RequestBody @NotNull OrderRequest dto) {
        OrderRequest orderRequest = orderService.createOrder(dto);
        return ResponseEntity.ok(orderRequest);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderRequest> fetchByOrderId(@PathVariable(value = "orderId") String orderId) {
        OrderRequest orderRequest = orderService.fetchByOrderId(Long.parseLong(orderId));
        return ResponseEntity.ok(orderRequest);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<OrderRequest>> fetchByOrderId() {
        List<OrderRequest> orderRequest = orderService.fetchAllOrders();
        return ResponseEntity.ok(orderRequest);
    }
}