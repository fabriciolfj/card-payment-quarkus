package com.github.card.exceptions.exception;

import com.github.card.exceptions.messages.ErrorsEnuns;

public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException() {
        super(ErrorsEnuns.STATUS_NOTFOUND.toMessage());
    }
}
