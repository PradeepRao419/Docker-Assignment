package com.example.order.repository;


import com.example.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.quantity >= :minQuantity")
    List<Order> findOrdersWithMinQuantity(@Param("minQuantity") Integer minQuantity);
}
