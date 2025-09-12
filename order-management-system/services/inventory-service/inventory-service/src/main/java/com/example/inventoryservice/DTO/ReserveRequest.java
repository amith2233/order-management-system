package com.example.inventoryservice.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReserveRequest(

       @NotNull UUID orderId,

       @NotNull UUID productId,

       @NotNull @Min(value = 1,message = "Quantity must be at least 1")
       int quantity,

       @NotBlank (message = "IdempotencyKey is required")
       String idempotencyKey

) {}
