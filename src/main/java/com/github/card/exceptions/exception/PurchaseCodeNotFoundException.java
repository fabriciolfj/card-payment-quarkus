package com.github.card.exceptions.exception;

public class PurchaseCodeNotFoundException extends RuntimeException {
    
    public PurchaseCodeNotFoundException(String code) {
        super("Purchase not found with code: " + code);
    }
}