package com.github.card.usecases.fraud;

import com.github.card.entities.physical.Purchase;

public interface GeoLocationGateway {

    Double process(final Purchase purchase);
}
