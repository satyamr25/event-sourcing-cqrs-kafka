package com.example.command.service;

import com.example.command.dto.OrderRequest;
import com.example.common.event.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderCommandService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderCommandService(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createOrder(OrderRequest request) {

        OrderEvent event = new OrderEvent(
                UUID.randomUUID().toString(),
                "ORDER_CREATED",
                request.getOrderId(),
                request.getAmount(),
                Instant.now()
        );

        kafkaTemplate.send("order-events", event.getOrderId(), event);
    }
}

