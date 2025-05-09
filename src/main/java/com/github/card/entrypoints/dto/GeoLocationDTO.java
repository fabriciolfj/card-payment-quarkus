package com.github.card.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoLocationDTO(@NotNull(message = "{geolation.latitude}")
                             double latitude,
                             @NotNull(message = "{geolation.longitude}")
                             double longitude) { }
