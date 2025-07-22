package com.example.paginacja.demo.controller;

import com.example.paginacja.demo.model.Order;
import com.example.paginacja.demo.model.dto.OrderProjection;
import com.example.paginacja.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Page<OrderProjection> listOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }

    @GetMapping("/items")
    public Page<Order> listOrdersWithItems(Pageable pageable) {
        return orderService.getOrdersWithItems(pageable);
    }
}