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

        var data = PurchaseData.builder()
                .customer(customer)
                .code(entity.code())
                .cards(cards)
                .registry(LocalDateTime.now())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .build();

        data.setStatus(entity
                .status()
                .stream()
                .map(v -> mapperStatusData(v, data, regsitry))
                .toList());

        return data;
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
                new GeoLocation(data.getLatitude(), data.getLongitude()));
    }

    public static PurchaseData enrichStatusData(final PurchaseData data, final List<Status> status) {
        var result = status
                .stream()
                .map(value -> mapperStatusData(value, data, LocalDateTime.now()))
                .toList();

        return data.addStatus(result);
    }

    private static PurchaseStatusData mapperStatusData(final Status status, final PurchaseData data, final LocalDateTime regsitry) {
        var id = new PurchaseStatusId();
        id.setStatus(status.name());
        id.setPurchase(data);
        return new PurchaseStatusData(
                id,
                regsitry);
    }
}
