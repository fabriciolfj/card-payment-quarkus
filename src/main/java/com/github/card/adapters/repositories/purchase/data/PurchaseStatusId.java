package com.github.card.adapters.repositories.purchase.data;

import io.quarkus.arc.All;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@All
@Embeddable
public class PurchaseStatusId {

    @Column(name = "purchase_id", nullable = false)
    private Long purchaseId;

    @Column(name = "status", nullable = false)
    private String status;
}