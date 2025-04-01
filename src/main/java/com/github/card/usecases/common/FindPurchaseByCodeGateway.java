package com.github.card.usecases.common;

import com.github.card.entities.physical.Purchase;

import java.util.Optional;

public interface FindPurchaseByCodeGateway {
    Optional<Purchase> findByCode(String code);
}