package com.example.common.event;

import java.time.Instant;

public class OrderEvent {

    private String eventId;
    private String eventType;
    private String orderId;
    private double amount;
    private Instant eventTime;

    public OrderEvent() {
    }

    public OrderEvent(String eventId,
                      String eventType,
                      String orderId,
                      double amount,
                      Instant eventTime) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.orderId = orderId;
        this.amount = amount;
        this.eventTime = eventTime;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public Instant getEventTime() {
        return eventTime;
    }
}
