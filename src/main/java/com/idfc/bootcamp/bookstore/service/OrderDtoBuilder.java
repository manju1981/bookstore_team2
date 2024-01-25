package com.idfc.bootcamp.bookstore.service;

import com.idfc.bootcamp.bookstore.dto.OrderDto;
import com.idfc.bootcamp.bookstore.dto.order_request.OrderItem;
import com.idfc.bootcamp.bookstore.dto.order_request.OrderRequest;
import com.idfc.bootcamp.bookstore.entity.OrderEntity;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@UtilityClass
public class OrderDtoBuilder {

    public static List<OrderDto> mapToOrderDto(OrderRequest dto) {
        List<OrderDto> list = new ArrayList<>();
        String orderId = RandomStringUtils.randomNumeric(5);
        for (OrderItem order : dto.getOrders()) {
            OrderDto orderDto = OrderDto.builder().orderId(Long.valueOf(orderId))
                    .countryId(dto.getCountryId()).bookId(order.getBookId()).quantity(order.getQuantity()).totalOrderValues(order.getQuantity() * order.getPrice()).build();
            list.add(orderDto);
        }
        return list;

    }

    public static OrderRequest mapToOrderRequest(List<OrderEntity> entities) {
        if (Objects.isNull(entities) || entities.isEmpty()){
            return OrderRequest.builder().build();
        }
        OrderRequest orderRequest = OrderRequest.builder().build();
        List<OrderItem> orderItems = entities.stream()
                .map(entity -> OrderItem.builder()
                        .bookId(entity.getBookId())
                        .quantity(entity.getQuantity())
                        .build())
                .collect(Collectors.toList());

        orderRequest.setOrders(orderItems);
        orderRequest.setCountryId(entities.get(0).getCountryId());
        orderRequest.setOrderId(entities.get(0).getOrderId());
        orderRequest.setTotalOrderValues(entities.stream()
                .mapToDouble(OrderEntity::getTotalOrderValues)
                .sum());
        return orderRequest;
    }


}
