package com.github.card.entities;

public record Purchase(Card card, Customer customer, GeoLocation geoLocation) { }
