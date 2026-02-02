package com.example.command.controller;

import com.example.command.dto.OrderRequest;
import com.example.command.service.OrderCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private final OrderCommandService service;

    public OrderCommandController(OrderCommandService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest request) {
        service.createOrder(request);
        return ResponseEntity.accepted().build(); // 202 Accepted is CORRECT
    }
}
