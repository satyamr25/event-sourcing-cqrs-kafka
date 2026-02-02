package com.example.command.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private String orderId;
    private Double amount;
}
