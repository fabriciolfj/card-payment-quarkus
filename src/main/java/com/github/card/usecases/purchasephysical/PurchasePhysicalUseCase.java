package com.github.card.usecases.purchasephysical;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.common.PurchaseSaveGateway;
import com.github.card.usecases.common.PurchaseUseCase;
import com.github.card.util.TypePurchaseConst;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
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
    @Transactional(Transactional.TxType.REQUIRED)
    public void execute(final Purchase purchase) {
        ofNullable(purchase)
                .ifPresentOrElse(
                        p -> {
                            gateway.process(p);
                            notifyGateway.process(p);
                        },
                        () -> {
                            throw new IllegalArgumentException("purchase cannot be null");
                        }
                );
    }

    @Override
    public String getType() {
        return TypePurchaseConst.PHYSICAL;
    }
}
