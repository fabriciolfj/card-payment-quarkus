package com.github.card.entrypoints.dto;

import java.util.List;

public record PurchaseResponseDTO(
    String code,
    CardDTO card,
    GeoLocationDTO geoLocation,
    String type,
    List<String> status
) {
    public record CardDTO(
        String crypt,
        String expirationDate,
        double balance
    ) {}
}