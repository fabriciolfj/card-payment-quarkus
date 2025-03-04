package com.github.card.usecases.purchasephysical;

import com.github.card.entities.physical.Purchase;

public interface PurchasePhysicalNotifyGateway {

    void process(Purchase purchase);
}
