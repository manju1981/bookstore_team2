package com.idfc.bootcamp.bookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfc.bootcamp.bookstore.controller.OrdersController;
import com.idfc.bootcamp.bookstore.dto.order_request.OrderItem;
import com.idfc.bootcamp.bookstore.dto.order_request.OrderRequest;
import com.idfc.bootcamp.bookstore.entity.BookEntity;
import com.idfc.bootcamp.bookstore.entity.CountryEntity;
import com.idfc.bootcamp.bookstore.entity.OrderEntity;
import com.idfc.bootcamp.bookstore.repository.BookRepository;
import com.idfc.bootcamp.bookstore.repository.CountryRepository;
import com.idfc.bootcamp.bookstore.repository.OrderRepository;
import com.idfc.bootcamp.bookstore.service.CountryService;
import com.idfc.bootcamp.bookstore.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    OrderService orderService;
    @InjectMocks
    private OrdersController orderController;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    CountryService countryService;
    @MockBean
    CountryRepository countryRepository;
    @MockBean
    OrderRepository orderRepository;

    public static OrderRequest createSampleOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(123L);
        orderRequest.setCountryId(1L);
        orderRequest.setTotalOrderValues(789.0);
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setBookId("ABC123");
        orderItem1.setQuantity(2);
        orderItems.add(orderItem1);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setBookId("XYZ789");
        orderItem2.setQuantity(1);
        orderItems.add(orderItem2);
        orderRequest.setOrders(orderItems);
        return orderRequest;
    }

    @Test
    void createBook() throws Exception {
        OrderRequest orderRequest = createSampleOrderRequest();
        when(orderRepository.findByOrderId(orderRequest.getOrderId())).thenReturn(null);
        when(countryService.findByCountryId(orderRequest.getCountryId())).thenReturn(Optional.of(CountryEntity.builder().id(1L).name("IND").build()));
        when(orderService.checkBookIdNotExist(orderRequest.getOrders())).thenReturn(false);
        when(bookRepository.findByBookId(any())).thenReturn(new BookEntity());
        when(bookRepository.findById(any())).thenReturn(Optional.of(new BookEntity()));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk());
                /*.andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(orderRequest.getOrderId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryId").value(orderRequest.getCountryId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalOrderValues").value(orderRequest.getTotalOrderValues()));*/
    }

    @Test
    void fetchByOrderId() throws Exception {
        OrderRequest orderRequest = createSampleOrderRequest();
        when(orderService.fetchByOrderId(any(Long.class))).thenReturn(orderRequest);
        when(orderService.createOrder(any(OrderRequest.class))).thenReturn(any());
        when(orderRepository.findByOrderId(orderRequest.getOrderId())).thenReturn(null);
        when(countryService.findByCountryId(orderRequest.getCountryId())).thenReturn(Optional.of(CountryEntity.builder().id(1L).name("IND").build()));
        when(orderService.checkBookIdNotExist(orderRequest.getOrders())).thenReturn(false);
        when(bookRepository.findByBookId(any())).thenReturn(new BookEntity());
        when(bookRepository.findById(any())).thenReturn(Optional.of(new BookEntity()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/{orderId}", "123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                /*.andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(orderRequest.getOrderId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryId").value(orderRequest.getCountryId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalOrderValues").value(orderRequest.getTotalOrderValues()));*/
    }
    @Test
    void fetchAllOrders_ShouldReturnOrderRequests() throws Exception {
        List<OrderEntity> sampleOrderEntities = createSampleOrderEntities();
        when(orderRepository.findAll()).thenReturn(sampleOrderEntities);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/fetch-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    private List<OrderEntity> createSampleOrderEntities() {
        List<OrderEntity> orderEntities = new ArrayList<>();
        OrderEntity orderEntity1 = new OrderEntity();
        orderEntity1.setOrderId(1L);
        orderEntity1.setCountryId(1L);
        orderEntity1.setBookId("ABC123");
        orderEntity1.setQuantity(2);
        orderEntity1.setTotalOrderValues(100.0);
        orderEntities.add(orderEntity1);
        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setOrderId(1L);
        orderEntity2.setCountryId(1L);
        orderEntity2.setBookId("XYZ789");
        orderEntity2.setQuantity(1);
        orderEntity2.setTotalOrderValues(50.0);
        orderEntities.add(orderEntity2);
        return orderEntities;
    }
}