package com.idfc.bootcamp.bookstore.repository;

import com.idfc.bootcamp.bookstore.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByOrderId(Long orderId);
}