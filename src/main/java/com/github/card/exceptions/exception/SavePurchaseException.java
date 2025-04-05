package com.github.card.exceptions.exception;

import static com.github.card.exceptions.messages.ErrorsEnuns.FAIL_SAVE_PURCHASE;

public class SavePurchaseException extends RuntimeException {

    public SavePurchaseException() {
        super(FAIL_SAVE_PURCHASE.toMessage());
    }
}
