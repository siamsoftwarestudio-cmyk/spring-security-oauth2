package com.example.security.oauth2.dto;

import com.example.security.oauth2.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderStatusRequest(
        @NotNull OrderStatus status
) {
}
