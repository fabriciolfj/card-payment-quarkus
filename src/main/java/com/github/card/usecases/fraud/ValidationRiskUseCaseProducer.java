package com.github.card.usecases.fraud;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.util.List;

@ApplicationScoped
public class ValidationRiskUseCaseProducer {

    @Produces
    public List<ValidationRiskUseCase> produceValidationRiskUseCases(
            ValidationRiskDistanceUseCase distanceUseCase,
            ValidationRiskAmountUseCase amountUseCase,
            FrequencyTransactionUseCase frequencyTransactionUseCase,
            HoursValidUseCase hoursValidUseCase,
            SpeedValidationUseCase speedValidationUseCase,
            MerchantValidationUseCase merchantValidationUseCase) {

        return List.of(distanceUseCase, amountUseCase, frequencyTransactionUseCase, hoursValidUseCase, speedValidationUseCase, merchantValidationUseCase);
    }
}
