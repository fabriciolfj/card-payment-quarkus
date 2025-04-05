package com.github.card.entrypoints.dto;

import jakarta.validation.constraints.NotNull;

public record CustomerDTO(@NotNull(message = "{customer.code}")
                          String code,
                          @NotNull(message = "{customer.geolocation.latitude}")
                          double latitude,
                          @NotNull(message = "{customer.geolocation.longitude}")
                          double longitude) { }
