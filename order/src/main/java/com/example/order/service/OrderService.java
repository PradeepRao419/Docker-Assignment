package com.example.order.service;

import com.example.order.exceptions.ResourceNotFoundException;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductConsumer productConsumer;

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8082/api/products";

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
        // Validate product existence from Kafka messages before updating the order
        existingOrder.setProductId(orderDetails.getProductId());
        existingOrder.setQuantity(orderDetails.getQuantity());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> recommendOrdersByMinQuantity(Integer minQuantity) {
        return orderRepository.findOrdersWithMinQuantity(minQuantity);
    }
}


