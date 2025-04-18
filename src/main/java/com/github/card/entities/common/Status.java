package com.github.card.entities.common;

import java.util.stream.Stream;

public enum Status {

    PENDENTE,
    ASSESSMENT,
    VALID,
    DENIED;

    public String getStatusDescribe() {
        return this.name();
    }

    public Status toStatus(final String describe) {
        return Stream.of(values())
                .filter(v -> v.name().equalsIgnoreCase(describe))
                .findFirst()
                .orElseThrow(() -> )
    }
}
