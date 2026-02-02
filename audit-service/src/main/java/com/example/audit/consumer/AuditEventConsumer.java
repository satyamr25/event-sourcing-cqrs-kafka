package com.example.audit.consumer;

import com.example.audit.event.OrderEvent;
import com.example.audit.model.EventAudit;
import com.example.audit.repository.EventAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AuditEventConsumer {

    private final EventAuditRepository repository;

    @KafkaListener(
            topics = "order-events",
            groupId = "audit-service-group"
    )
    public void consume(OrderEvent event) {

        EventAudit audit = new EventAudit(
                event.getEventId(),
                event.getEventType(),
                event.toString(),
                Instant.now()
        );

        repository.save(audit);
    }
}
