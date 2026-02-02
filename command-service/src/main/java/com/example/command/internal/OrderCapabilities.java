package com.example.command.internal;

public record OrderCapabilities(
        boolean canCancel,
        boolean canModify,
        String status
) {}

