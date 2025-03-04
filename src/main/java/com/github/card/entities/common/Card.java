package com.github.card.entities.common;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Card(String crypt, LocalDate expirationDate, BigDecimal value) { }
