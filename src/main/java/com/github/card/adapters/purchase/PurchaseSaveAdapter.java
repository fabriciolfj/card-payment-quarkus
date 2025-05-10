package com.github.card.adapters.purchase;

import com.github.card.adapters.repositories.purchase.repository.PurchaseRepository;
import com.github.card.entities.physical.Purchase;
import com.github.card.exceptions.exception.SavePurchaseException;
import com.github.card.usecases.common.PurchaseSaveGateway;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.card.adapters.purchase.PurchaseDataMapper.toData;

@RequiredArgsConstructor
@ApplicationScoped
public class PurchaseSaveAdapter implements PurchaseSaveGateway {

    private static final Logger log = LoggerFactory.getLogger(PurchaseSaveAdapter.class);

    private final PurchaseRepository repository;
    private final EnrichPurchaseAdapter enrichPurchaseAdapter;

    @Override
    public void process(Purchase purchase) {
        try {
            var data = enrichPurchaseAdapter.process(toData(purchase));

            repository.executeSave(data);
            log.info("saved success purchase {}", purchase.code());
        } catch (Exception e) {
            log.error("fail save purchase, details {}", e.getMessage());
            throw new SavePurchaseException();
        }
    }
}
