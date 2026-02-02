package com.example.command.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String orderId;
    private Double amount;
}
