package com.example.inventoryservice.DTO;

import java.time.Instant;
import java.util.UUID;

public record ReserveResponse (
        UUID reservationId,
        UUID orderId,
        UUID productId,
        int quantity,
        String reservationStatus,
        String idempotencyKey,
        Instant createdAt,
        Instant updatedAt
) { }
