package com.github.card.usecases.purchasephysical;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.common.PurchaseSaveGateway;
import com.github.card.usecases.common.PurchaseUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@ApplicationScoped
@Named("purchasePhysical")
public class PurchasePhysicalUseCase implements PurchaseUseCase {

    private final PurchaseSaveGateway gateway;
    private final PurchasePhysicalNotifyGateway notifyGateway;

    @Override
    public void execute(Purchase purchase) {
        ofNullable(purchase)
                .map(p -> {
                    gateway.process(p);
                    return p;
                })
                .map(p -> {
                    notifyGateway.process(p);
                    return p;
                })
                .orElseThrow(() -> new IllegalArgumentException("purchase cannot be null"));
    }
}
