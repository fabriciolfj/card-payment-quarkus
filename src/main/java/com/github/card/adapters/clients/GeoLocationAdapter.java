package com.github.card.adapters.clients;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.fraud.GeoLocationGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Slf4j
@ApplicationScoped
public class GeoLocationAdapter implements GeoLocationGateway {

    private final GeoLocationClient client;

    @Inject
    public GeoLocationAdapter(@RestClient GeoLocationClient client) {
        this.client = client;
    }

    @Override
    @Retry(
        maxRetries = 5,
        maxDuration = 5
    )
    public Double process(final Purchase purchase) {
        final var request = GeoLocalizationRequestMapper.toRequest(purchase);
        log.info("request prepared {}", request);

        final var result = client.request(request);
        return result.getDistance();
    }
}