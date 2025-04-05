package com.github.card.adapters.purchase;

import com.github.card.adapters.repositories.customer.repository.CustomerRepository;
import com.github.card.adapters.repositories.purchase.data.PurchaseData;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class EnrichPurchaseAdapter {

    private final CustomerRepository customerRepository;

    public PurchaseData process(final PurchaseData data) {
        var customer = customerRepository.findByCode(data.getCustomer());
        data.setCustomer(customer);

        return data;
    }
}
