package com.example.inventoryservice.controller;

import com.example.inventoryservice.DTO.SeedRequest;
import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/seed")
    public ResponseEntity<InventoryItem> seedInventory(@Valid @RequestBody SeedRequest seedRequest) {
        InventoryItem item= inventoryService.seedInventory(seedRequest);
        if(item.getCreatedAt().equals(item.getUpdatedAt())) return ResponseEntity.status(HttpStatus.CREATED).body(item);
        return ResponseEntity.ok(item);

    }






















}
