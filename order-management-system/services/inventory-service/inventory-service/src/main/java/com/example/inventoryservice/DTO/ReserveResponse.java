package com.example.inventoryservice.DTO;

import com.example.inventoryservice.model.Reservation;
import com.example.inventoryservice.model.ReservationStatus;

import java.util.UUID;

public record ReserveResponse (
        UUID reservationId,
        UUID orderId,
        UUID productId,
        int quantity,
        String reservationStatus
) {
    public ReserveResponse(Reservation reservation){
        this(reservation.getId(),reservation.getOrderId(),reservation.getProductId(),reservation.getQuantity(),reservation.getReservationStatus().name());
    }
    public ReserveResponse(Reservation reservation, String reservationStatus){
        this(reservation.getId(),reservation.getOrderId(),reservation.getProductId(),reservation.getQuantity(),reservationStatus);
    }
}
