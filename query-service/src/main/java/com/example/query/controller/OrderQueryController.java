package com.example.query.controller;

import com.example.query.model.OrderView;
import com.example.query.repository.OrderViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderQueryController {

    private final OrderViewRepository repository;

    @GetMapping("/{id}")
    public OrderView getOrder(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
