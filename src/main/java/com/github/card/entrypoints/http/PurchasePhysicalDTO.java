package com.github.card.entrypoints.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PurchasePhysicalDTO(@NotBlank(message = "{purchase.code}")
                                  String code,
                                  @NotNull(message = "{purchase.expiration}")
                                  LocalDate expirationDate,
                                  @NotNull(message = "{purchase.value}")
                                  @Positive(message = "{purchase.value}")
                                  BigDecimal value,
                                  @NotBlank(message = "{purchase.crypt}")
                                  String crypt,
                                  @NotNull(message = "{purchase.customer}")
                                  CustomerDTO customer,
                                  @NotNull(message = "{purchase.geo}")
                                  GeoLocationDTO geo){ }

