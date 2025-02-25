package com.github.card.entrypoints;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CardDTO(@NotBlank(message = "{card.code}") String code,
                      @NotNull(message = "{card.expiration}")
                      LocalDate expirationDate,
                      @NotNull(message = "{card.purchase}")
                      @Positive(message = "{card.purchase.value}")
                      BigDecimal purchase,
                      @NotBlank(message = "{card.customer}")
                      String customer){ }

