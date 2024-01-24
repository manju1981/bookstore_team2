package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.dto.QuantityDto;
import com.idfc.bootcamp.bookstore.dto.order_request.OrderItem;
import com.idfc.bootcamp.bookstore.dto.order_request.OrderRequest;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.entity.OrderEntity;
import com.idfc.bootcamp.bookstore.enums.Type;
import com.idfc.bootcamp.bookstore.exceptions.ApiErrors;
import com.idfc.bootcamp.bookstore.exceptions.ApplicationException;
import com.idfc.bootcamp.bookstore.repository.OrderRepository;
import com.idfc.bootcamp.bookstore.util.MapperUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private CountryService countryService;

    @Transactional
    public OrderRequest createOrder(OrderRequest dto) {
        validateAndSetPrice(dto);
        List<OrderEntity> orderEntityList = orderRepository.saveAll(MapperUtility.mapList(OrderDtoBuilder.mapToOrderDto(dto), OrderEntity.class));
        return OrderDtoBuilder.mapToOrderRequest(orderEntityList);
    }
    private void validateAndSetPrice(OrderRequest dto) {
        if (isValidationConditionMet(dto)) {
            throw new ApplicationException(ApiErrors.INIT_VALIDATION_FAILED, dto.getOrderId());
        }
        setPrice(dto);
    }

    private boolean isValidationConditionMet(OrderRequest dto) {
        return (Objects.nonNull(dto.getOrderId()) && Objects.nonNull(orderRepository.findByOrderId(dto.getOrderId())))
                || countryService.findByCountryId(dto.getCountryId()).isEmpty()
                || checkBookIdNotExist(dto.getOrders());
    }
    public boolean checkBookIdNotExist(List<OrderItem> orders) {
        for (OrderItem order : orders) {
            BookEntity book = bookService.findByBookId(order.getBookId());
            if (Objects.isNull(book)) {
                return true;
            }
        }
        for (OrderItem order : orders) {
            reduceStockOfBook(order.getBookId(), order.getQuantity());
        }
        return false;
    }
    private void reduceStockOfBook(String bookId, int quantity) {
        BookEntity book = bookService.findByBookId(bookId);
        bookService.update(book.getId(), QuantityDto.builder().quantity(quantity).type(Type.REMOVE).build());
    }

    private void setPrice(OrderRequest dto) {
        for (OrderItem order : dto.getOrders()) {
            BookEntity book = bookService.findByBookId(order.getBookId());
            order.setPrice(book.getPrice());
        }
    }

    public OrderRequest fetchByOrderId(Long orderId) {
        List<OrderEntity> list = orderRepository.findByOrderId(orderId);
        return OrderDtoBuilder.mapToOrderRequest(list);
    }

    public List<OrderRequest> fetchAllOrders() {
        List<OrderEntity> allOrders = orderRepository.findAll();
        Map<Long, OrderRequest> orderRequestMap = new HashMap<>();
        for (OrderEntity order : allOrders) {
            OrderRequest orderRequest = orderRequestMap.computeIfAbsent(order.getOrderId(), key -> {
                OrderRequest newOrderRequest = new OrderRequest();
                newOrderRequest.setOrderId(order.getOrderId());
                newOrderRequest.setCountryId(order.getCountryId());
                newOrderRequest.setTotalOrderValues(0.0);
                newOrderRequest.setOrders(new ArrayList<>());
                return newOrderRequest;
            });
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(order.getBookId());
            orderItem.setQuantity(order.getQuantity());
            orderRequest.getOrders().add(orderItem);
            orderRequest.setTotalOrderValues(orderRequest.getTotalOrderValues() + order.getTotalOrderValues());
        }
        return new ArrayList<>(orderRequestMap.values());
    }
}