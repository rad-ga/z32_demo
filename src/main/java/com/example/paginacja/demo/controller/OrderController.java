package com.example.paginacja.demo.controller;

import com.example.paginacja.demo.model.Order;
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
    public Page<Order> listOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }
}