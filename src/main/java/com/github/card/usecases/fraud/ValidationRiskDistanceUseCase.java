package com.github.card.usecases.fraud;

import com.github.card.entities.physical.Purchase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequiredArgsConstructor
@ApplicationScoped
public class ValidationRiskDistanceUseCase implements ValidationRiskUseCase {

    @ConfigProperty(name = "payment.max.distance.km", defaultValue = "500.0")
    double maxDistanceKm;

    private final GeoLocationGateway getLocationGateway;

    @Override
    public Boolean execute(Purchase entity) {
        return getLocationGateway.process(entity)
                .compareTo(maxDistanceKm) < 1;
    }
}
