package com.github.card.usecases.fraud;

import com.github.card.entities.physical.Purchase;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.math.BigDecimal;

@ApplicationScoped
public class ValidationRiskAmountUseCase implements ValidationRiskUseCase{

    @ConfigProperty(name = "payment.threshold.amount", defaultValue = "1000.0")
    private double thresholdAmount;

    private static final int GREATER_THAN = 0;

    @Override
    public Boolean execute(Purchase entity) {
        return entity.getValue().compareTo(new BigDecimal(thresholdAmount)) > GREATER_THAN;
    }
}
