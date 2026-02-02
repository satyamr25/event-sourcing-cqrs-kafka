package com.example.query.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderView {

    @Id
    private String orderId;

    private Double amount;
    private String status;
}
