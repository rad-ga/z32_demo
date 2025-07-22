package com.example.paginacja.demo.service;

import com.example.paginacja.demo.model.Order;
import com.example.paginacja.demo.model.dto.OrderProjection;
import com.example.paginacja.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Page<OrderProjection> getOrders(Pageable pageable) {
        return orderRepository.findAllByProjection(pageable);
    }

    public Page<Order> getOrdersWithItems(Pageable pageable) {
        return orderRepository.findAllWithItems(pageable);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}