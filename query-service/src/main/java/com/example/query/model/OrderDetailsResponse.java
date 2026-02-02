package com.example.query.model;

public record OrderDetailsResponse(
        OrderView order,
        OrderCapabilities capabilities
) {}
