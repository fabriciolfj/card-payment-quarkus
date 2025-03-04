package com.github.card.usecases.purchasevirtual;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.common.PurchaseUseCase;
import com.github.card.util.TypePurchaseConst;
import jakarta.enterprise.context.ApplicationScoped;

import javax.inject.Named;

@ApplicationScoped
@Named("purchaseVirtual")
public class PurchaseVirtualUseCase implements PurchaseUseCase {

    @Override
    public void execute(Purchase purchase) {

    }

    @Override
    public String getType() {
        return TypePurchaseConst.VIRTUAL;
    }
}
