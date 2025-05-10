package com.github.card.entities.physical;

import com.github.card.entities.common.Card;
import com.github.card.entities.common.Customer;
import com.github.card.entities.common.Status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Purchase(String code,
                       List<Status> status,
                       Card card,
                       Customer customer,
                       GeoLocation geoLocation) {

    public double getLongitude() {
        return geoLocation.longitude();
    }

    public double getLatitude() {
        return geoLocation.latitude();
    }

    public BigDecimal getValue() {
        return card.value();
    }

    public double getLongitudeCustomer() {
        return customer.longitude();
    }

    public double getLatitudeCustomer() {
        return customer.latitude();
    }

    public List<String> getStatusDescribe() {
        return status.stream().map(Enum::toString).toList();
    }

    public Purchase updateStatus(final Status status) {
        final var newStatusList = Stream.concat(
                        this.status.stream().filter(c -> !c.getStatusDescribe().equals(status.getStatusDescribe())),
                        Stream.of(status)
                )
                .collect(Collectors.toList());

        return new Purchase(this.code, newStatusList, this.card, this.customer, this.geoLocation);
    }
}
