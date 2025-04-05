package com.github.card.entrypoints.http;

import com.github.card.entities.common.Card;
import com.github.card.entities.physical.GeoLocation;

public record PurchaseResponseDTO(
    String code,
    CardDTO card,
    GeoLocationDTO geoLocation,
    String type
) {
    public record CardDTO(
        String crypt,
        String expirationDate,
        double balance
    ) {}
}