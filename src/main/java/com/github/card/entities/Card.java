package com.github.card.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Card(String code, LocalDate expirationDate, BigDecimal balance) { }
