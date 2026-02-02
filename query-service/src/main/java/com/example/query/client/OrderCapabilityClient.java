package com.example.query.client;

import com.example.query.model.OrderCapabilities;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "command-service",
        url = "http://command-service:8081"
)
public interface OrderCapabilityClient {

    @GetMapping("/internal/orders/{orderId}/capabilities")
    OrderCapabilities getCapabilities(
            @PathVariable("orderId") String orderId
    );
}
