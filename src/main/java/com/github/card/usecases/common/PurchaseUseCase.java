package com.github.card.usecases.common;

import com.github.card.entities.physical.Purchase;

public interface PurchaseUseCase {

    void execute(final Purchase purchase);
}
