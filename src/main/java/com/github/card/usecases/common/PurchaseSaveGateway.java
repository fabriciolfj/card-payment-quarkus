package com.github.card.usecases.common;

import com.github.card.entities.physical.Purchase;

public interface PurchaseSaveGateway {

    void process(Purchase purchase);
}
