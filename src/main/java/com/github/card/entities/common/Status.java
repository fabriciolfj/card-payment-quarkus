package com.github.card.entities.common;

import com.github.card.exceptions.exception.StatusNotFoundException;

import java.util.stream.Stream;

public enum Status {

    PENDENTE,
    ASSESSMENT,
    VALID,
    DENIED;

    public String getStatusDescribe() {
        return this.name();
    }

    public static Status toStatus(final String describe) {
        return Stream.of(values())
                .filter(v -> v.name().equalsIgnoreCase(describe))
                .findFirst()
                .orElseThrow(StatusNotFoundException::new);
    }
}
