package com.example.command.internal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/orders")
public class OrderCapabilityController {

    @GetMapping("/{orderId}/capabilities")
    public OrderCapabilities getCapabilities(@PathVariable String orderId) {
        return new OrderCapabilities(
                true,   // canCancel
                false,  // canModify
                "ACTIVE"
        );
    }
}
