package com.example.query.controller;

import com.example.query.model.OrderCapabilities;
import com.example.query.client.OrderCapabilityClient;
import com.example.query.model.OrderDetailsResponse;
import com.example.query.model.OrderView;
import com.example.query.repository.OrderViewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {

    private final OrderViewRepository repository;

    private final OrderCapabilityClient capabilityClient;

    public OrderQueryController(OrderViewRepository repository, OrderCapabilityClient capabilityClient) {
        this.repository = repository;
        this.capabilityClient = capabilityClient;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailsResponse> getOrder(@PathVariable String orderId) {
        OrderView order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderCapabilities capabilities =
                capabilityClient.getCapabilities(orderId);

        OrderDetailsResponse response =
                new OrderDetailsResponse(order, capabilities);

        return ResponseEntity.ok(response);
    }
}
