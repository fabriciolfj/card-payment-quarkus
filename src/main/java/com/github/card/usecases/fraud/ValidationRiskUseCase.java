package com.github.card.usecases.fraud;

import com.github.card.entities.physical.Purchase;

public interface ValidationRiskUseCase {

    Boolean execute(final Purchase entity);
}
