package com.example.inventoryservice.controller;

import com.example.inventoryservice.DTO.ReserveRequest;
import com.example.inventoryservice.DTO.ReserveResponse;
import com.example.inventoryservice.DTO.SeedRequest;
import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.model.Reservation;
import com.example.inventoryservice.model.ReservationStatus;
import com.example.inventoryservice.service.InventoryService;
import com.example.inventoryservice.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    private final ReservationService reservationService;

    @PostMapping("/seed")
    public ResponseEntity<InventoryItem> seedInventory(@Valid @RequestBody SeedRequest seedRequest) {
        InventoryItem item= inventoryService.seedInventory(seedRequest);
        if(item.getCreatedAt().equals(item.getUpdatedAt())) return ResponseEntity.status(HttpStatus.CREATED).body(item);
        return ResponseEntity.ok(item);

    }

    @PostMapping("/reservations")
    public ResponseEntity<ReserveResponse> reserveInventory(@Valid @RequestBody ReserveRequest reserveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.reserve(reserveRequest));

    }

    @PostMapping("/reservations/{id}/release")
    public ResponseEntity<ReserveResponse> releaseInventory(@PathVariable UUID id) {
        Reservation reservation = reservationService.release(id);
        ReserveResponse response=new ReserveResponse(reservation, ReservationStatus.RELEASED.name());
        return ResponseEntity.ok(response);
    }


}
