package com.github.card.entities.physical;

import com.github.card.entities.common.Card;
import com.github.card.entities.common.Customer;
import com.github.card.entities.common.Status;

import java.math.BigDecimal;
import java.util.List;

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

    public List<String> getStatusDecribe() {
        return status.stream().map(Enum::toString).toList();
    }

    public Purchase updateStatus(final Status status) {
        this.status.add(status);
        return this;
    }
}
