package com.github.card.adapters.purchase;

import com.github.card.adapters.repositories.purchase.data.PurchaseData;
import com.github.card.adapters.repositories.purchase.data.PurchaseStatusData;
import com.github.card.adapters.repositories.purchase.data.PurchaseStatusId;
import com.github.card.entities.common.Status;
import com.github.card.entities.physical.GeoLocation;
import com.github.card.entities.physical.Purchase;

import java.time.LocalDateTime;
import java.util.List;


public class PurchaseDataMapper {

    private PurchaseDataMapper() {
    }

    public static PurchaseData toData(final Purchase entity) {
        var cards = List.of(CardDataMapper.INSTANCE.toData(entity.card()));
        var customer = CustomerDataMapper.INSTANCE.toData(entity.customer());
        var regsitry = LocalDateTime.now();

        return PurchaseData.builder()
                .customer(customer)
                .cards(cards)
                .status(entity
                        .status()
                        .stream()
                        .map(v ->
                                new PurchaseStatusData(
                                        new PurchaseStatusId(null, v.name()),
                                        regsitry)
                        )
                        .toList()
                )
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .build();
    }

    public static Purchase toEntity(final PurchaseData data) {
        var card = CardDataMapper.INSTANCE.toEntity(data.getCards().getFirst());
        var customer = CustomerDataMapper.INSTANCE.toEntity(data.getCustomer());

        return new Purchase(data.getCode(),
                data.getStatus()
                        .stream()
                        .map(v ->
                                Status.valueOf(v.getStatus())).toList(),
                card,
                customer,
                new GeoLocation(data.getLongitude(), data.getLongitude()));
    }
}
