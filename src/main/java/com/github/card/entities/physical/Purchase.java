package com.github.card.entities.physical;

import com.github.card.entities.common.Card;
import com.github.card.entities.common.Customer;

public record Purchase(String code, Card card, Customer customer, GeoLocation geoLocation) { }
