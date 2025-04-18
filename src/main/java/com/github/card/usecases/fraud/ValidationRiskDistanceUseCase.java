package com.github.card.usecases.fraud;

import com.github.card.entities.physical.Purchase;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ValidationRiskDistanceUseCase implements ValidationRiskUseCase {

    @ConfigProperty(name = "payment.max.distance.km", defaultValue = "500.0")
    double maxDistanceKm;

    @Override
    public Boolean execute(Purchase entity) {
        return null;
    }
}
