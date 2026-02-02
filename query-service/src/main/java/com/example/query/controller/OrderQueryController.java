package com.example.query.controller;

import com.example.query.model.OrderView;
import com.example.query.repository.OrderViewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {

    private final OrderViewRepository repository;

    public OrderQueryController(OrderViewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderView> getOrder(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
