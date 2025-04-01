package com.github.card.adapters.repositories.purchase.repository;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.common.FindPurchaseByCodeGateway;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RequiredArgsConstructor
@ApplicationScoped
public class FindPurchaseByCodeAdapter implements FindPurchaseByCodeGateway {

    private static final Logger log = LoggerFactory.getLogger(FindPurchaseByCodeAdapter.class);
    
    private final PurchaseRepository repository;
    
    @Override
    public Optional<Purchase> findByCode(String code) {
        log.info("Finding purchase by code: {}", code);
        return repository.findByCode(code)
                .map(PurchaseDataMapper.INSTANCE::toDomain);
    }
}