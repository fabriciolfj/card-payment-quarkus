package com.github.card.adapters.repositories.purchase.repository;

import com.github.card.adapters.repositories.purchase.data.PurchaseData;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class PurchaseRepository implements PanacheRepository<PurchaseData> {

    public void executeSave(PurchaseData data) {
        persist(data);
    }
    
    public Optional<PurchaseData> findByCode(String code) {
        return find("code", code).firstResultOptional();
    }
}
