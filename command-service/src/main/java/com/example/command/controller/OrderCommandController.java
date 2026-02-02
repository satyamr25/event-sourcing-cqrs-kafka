package com.example.command.controller;

import com.example.command.dto.CreateOrderRequest;
import com.example.command.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderCommandController {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request) {

        OrderEvent event = new OrderEvent(
                UUID.randomUUID().toString(),
                "ORDER_CREATED",
                request.getOrderId(),
                request.getAmount(),
                Instant.now()
        );

        kafkaTemplate.send("order-events", event);

        return ResponseEntity.accepted().build(); // 202
    }
}
