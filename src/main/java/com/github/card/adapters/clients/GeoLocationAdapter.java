package com.github.card.adapters.clients;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.fraud.GeoLocationGateway;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class GeoLocationAdapter implements GeoLocationGateway {

    private final GeoLocationClient client;

    @Override
    public Double process(final Purchase purchase) {
        final var request = GeoLocalizationRequestMapper.toRequest(purchase);
        log.info("request prepared {}", request);

        final var result = client.request(request);
        return result.getDistance();
    }
}
