package com.github.card.usecases.fraud;

import com.github.card.entities.physical.Purchase;

public interface UpdateStatusPurchaseGateway {

    void process(Purchase purchase);
}
