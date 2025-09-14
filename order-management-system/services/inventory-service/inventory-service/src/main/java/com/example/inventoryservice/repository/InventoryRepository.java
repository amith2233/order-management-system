package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, UUID> {

    Optional<InventoryItem> findByProductId(UUID productId);


    @Modifying
    @Query(""" 
        UPDATE InventoryItem i
            SET i.availableQty=i.availableQty-:qty,
                i.reservedQty=i.reservedQty+:qty
            WHERE i.productId=:productId
                AND i.availableQty>=:qty
    """)
    int tryReserve(@Param("productId") UUID productId,@Param("qty") int qty);

    @Modifying
    @Query(""" 
        UPDATE InventoryItem i
            SET i.availableQty=i.availableQty+:qty,
                i.reservedQty=i.reservedQty-:qty
            WHERE i.productId=:productId
                AND i.reservedQty>=:qty
    """)
    int tryRelease(@Param("productId") UUID productId,@Param("qty") int qty);


}
