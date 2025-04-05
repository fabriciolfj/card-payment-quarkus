package com.github.card.entrypoints.dto;

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