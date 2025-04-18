package com.github.card.exceptions.messages;

import java.util.ResourceBundle;

public enum ErrorsEnuns {

    STATUS_NOTFOUND,
    FAIL_SAVE_PURCHASE;

    public String toMessage() {
        final ResourceBundle bundle = ResourceBundle.getBundle("exceptions/exceptions");
        return bundle.getString(this.name() + ".message");
    }
}
