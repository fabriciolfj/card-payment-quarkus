package com.github.card.adapters.repositories.purchase.repository;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.common.PurchaseSaveGateway;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@ApplicationScoped
public class PurchaseSaveAdapter implements PurchaseSaveGateway {

    private static final Logger log = LoggerFactory.getLogger(PurchaseSaveAdapter.class);

    private final PurchaseRepository repository;

    @Override
    public void process(Purchase purchase) {
        var data = PurchaseDataMapper.INSTANCE.toData(purchase);

        repository.executeSave(data);
        log.info("saved success purchase {}", purchase.code());
    }
}
