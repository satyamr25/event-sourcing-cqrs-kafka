package com.example.audit.consumer;


import com.example.common.event.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuditEventListener {

    @KafkaListener(
            topics = "order-events",
            groupId = "audit-service-group"
    )
    public void audit(OrderEvent event) {

        System.out.println(
                "[AUDIT] Order " + event.getOrderId() +
                        " amount=" + event.getAmount() +
                        " at " + event.getEventTime()
        );
    }
}
