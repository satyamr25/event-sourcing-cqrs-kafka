package com.example.query.event;

import lombok.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private String eventId;
    private String eventType;   // ORDER_CREATED, ORDER_CANCELLED
    private String orderId;
    private Double amount;
    private Instant eventTime;
}
