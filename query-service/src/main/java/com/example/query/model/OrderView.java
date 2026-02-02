package com.example.query.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.Instant;

@Entity
public class OrderView {

    @Id
    private String orderId;

    private double amount;
    private Instant createdAt;

    protected OrderView() {}

    public OrderView(String orderId, double amount, Instant createdAt) {
        this.orderId = orderId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
