package com.github.card.adapters.purchase;

import com.github.card.adapters.repositories.purchase.data.PurchaseData;
import com.github.card.entities.physical.Purchase;
import java.util.List;


public class PurchaseDataMapper {

    private PurchaseDataMapper() { }

    public static PurchaseData toData(final Purchase entity) {
        var cards = List.of(CardDataMapper.INSTANCE.toData(entity.card()));

        var customer = CustomerDataMapper.INSTANCE.toData(entity.customer());

        return PurchaseData.builder()
                .customer(customer)
                .cards(cards)
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .build();
    }
}
