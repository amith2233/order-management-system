package com.example.inventoryservice.service;

import com.example.inventoryservice.DTO.ReserveRequest;
import com.example.inventoryservice.DTO.ReserveResponse;
import com.example.inventoryservice.model.Reservation;
import com.example.inventoryservice.model.ReservationStatus;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final InventoryRepository inventoryRepository;

    @Transactional
    public ReserveResponse reserve(ReserveRequest reserveRequest) {
        var existing = reservationRepository.findByIdempotencyKey(reserveRequest.idempotencyKey());
        if (existing.isPresent()) {
            var reservation = existing.get();
            return new ReserveResponse(reservation);
        }

        inventoryRepository.findByProductId(reserveRequest.productId()).orElseThrow(()
                -> new IllegalArgumentException("Product not found"));

        var change=inventoryRepository.tryReserve(reserveRequest.productId(), reserveRequest.quantity());
        if(change==0) throw new IllegalStateException("Insufficient stock");

        var saved= reservationRepository.save(
                Reservation.builder()
                        .orderId(reserveRequest.orderId())
                        .productId(reserveRequest.productId())
                        .quantity(reserveRequest.quantity())
                        .idempotencyKey(reserveRequest.idempotencyKey())
                        .build()
        );
        return new ReserveResponse(saved);


    }

    @Transactional
    public Reservation release(UUID reservationId) {
        var reservation = reservationRepository.findById(reservationId).orElseThrow(()->new IllegalArgumentException("Reservation not found"));
        var updated=inventoryRepository.tryRelease(reservation.getProductId(), reservation.getQuantity());
        if(updated==0) throw new IllegalStateException("failed to release inventory");
        reservationRepository.delete(reservation);
        return reservation;
    }
}
