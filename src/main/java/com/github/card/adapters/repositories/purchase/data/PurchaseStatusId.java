package com.github.card.adapters.repositories.purchase.data;

import io.quarkus.arc.All;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@All
@Embeddable
public class PurchaseStatusId {

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseData purchase;

    @Column(name = "status", nullable = false)
    private String status;
}