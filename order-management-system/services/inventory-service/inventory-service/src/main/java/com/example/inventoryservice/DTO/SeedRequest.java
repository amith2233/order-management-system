package com.example.inventoryservice.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//seed - to add record in inventory if product id is not there and update quantity if present
public record SeedRequest (
    @NotNull UUID productId,
    @NotNull  @Min(0) int availableQty
){ }
