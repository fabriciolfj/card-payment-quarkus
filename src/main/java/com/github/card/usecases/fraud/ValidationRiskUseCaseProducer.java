package com.github.card.usecases.fraud;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.util.List;

@ApplicationScoped
public class ValidationRiskUseCaseProducer {

    @Produces
    public List<ValidationRiskUseCase> produceValidationRiskUseCases(
            ValidationRiskDistanceUseCase distanceUseCase,
            ValidationRiskAmountUseCase amountUseCase) {

        return List.of(distanceUseCase, amountUseCase);
    }
}
