package com.example.query.consumer;

import com.example.query.event.OrderEvent;
import com.example.query.model.OrderView;
import com.example.query.repository.OrderViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final OrderViewRepository repository;

    @KafkaListener(
            topics = "order-events",
            groupId = "query-service-group"
    )
    public void consume(OrderEvent event) {

        if ("ORDER_CREATED".equals(event.getEventType())) {
            OrderView view = new OrderView(
                    event.getOrderId(),
                    event.getAmount(),
                    "CREATED"
            );
            repository.save(view);
        }
    }
}
