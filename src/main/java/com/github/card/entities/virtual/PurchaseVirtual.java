package com.github.card.entities.virtual;

import com.github.card.entities.common.Card;
import com.github.card.entities.common.Customer;

public record PurchaseVirtual(Card card, Customer customer, PurchaseSites purchaseSites) { }
