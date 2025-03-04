package com.github.card.usecases.common;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class PurchaseUseCaseProducer {

    @Produces
    @ApplicationScoped
    public Map<String, PurchaseUseCase> producePurchaseUseCases(final Instance<PurchaseUseCase> useCases) {
        Map<String, PurchaseUseCase> useCaseMap = new HashMap<>();

        for (PurchaseUseCase useCase : useCases) {
            useCaseMap.put(useCase.getType(), useCase);
        }

        return useCaseMap;
    }
}
