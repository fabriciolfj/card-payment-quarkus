package com.github.card.adapters.repositories.purchase.repository;

import com.github.card.adapters.repositories.purchase.data.PurchaseData;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PurchaseRepository implements PanacheRepository<PurchaseData> {

    public void executeSave(PurchaseData data) {
        persist(data);
    }
}
