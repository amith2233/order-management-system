package com.example.inventoryservice.service;

import com.example.inventoryservice.DTO.SeedRequest;
import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public InventoryItem seedInventory(SeedRequest seedRequest) {
        return inventoryRepository.findByProductId(seedRequest.productId())
                .map(item -> {
                    item.setAvailableQty(seedRequest.availableQty());
                    return inventoryRepository.save(item);
                })
                .orElseGet(() -> inventoryRepository.save(
                        InventoryItem.builder()
                                .productId(seedRequest.productId())
                                .availableQty(seedRequest.availableQty())
                                .build()
                ));
    }











}
