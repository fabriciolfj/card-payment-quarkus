package com.github.card.usecases.common;

import com.github.card.entities.physical.Purchase;

public interface FindPurchaseByCodeGateway {

    Purchase findByCode(String code);
}