package com.github.card.adapters.clients;

import com.github.card.entities.physical.Purchase;

public class GeoLocalizationRequestMapper {

    private GeoLocalizationRequestMapper() { }

    public static GeoLocalizationRequestDTO toRequest(final Purchase purchase) {
        return GeoLocalizationRequestDTO
                .builder()
                .sourceLatitude(purchase.getLatitudeCustomer())
                .sourceLongitude(purchase.getLongitudeCustomer())
                .targetLatitude(purchase.getLatitude())
                .targetLongitude(purchase.getLongitude())
                .build();
    }
}
