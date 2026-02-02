package com.example.query.consumer;

import com.example.common.event.OrderEvent;
import com.example.query.model.OrderView;
import com.example.query.repository.OrderViewRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    private final OrderViewRepository repository;

    public OrderEventListener(OrderViewRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(
            topics = "order-events",
            groupId = "query-service-group"
    )
    public void handle(OrderEvent event) {

        OrderView view = new OrderView(
                event.getOrderId(),
                event.getAmount(),
                event.getEventTime()
        );

        repository.save(view);
    }
}
