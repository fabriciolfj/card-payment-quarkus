package com.github.card.adapters.repositories.purchase.data;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "purchase_status")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseStatusData {

    @EmbeddedId
    private PurchaseStatusId id;

    @Column(name = "registry", nullable = false)
    private LocalDateTime registry;

    public String getStatus() {
        return id.getStatus();
    }
}
