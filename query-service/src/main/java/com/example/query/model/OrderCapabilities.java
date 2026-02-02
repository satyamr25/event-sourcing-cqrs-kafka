package com.example.query.model;

public record OrderCapabilities(
        boolean canCancel,
        boolean canModify,
        String status
) {}

