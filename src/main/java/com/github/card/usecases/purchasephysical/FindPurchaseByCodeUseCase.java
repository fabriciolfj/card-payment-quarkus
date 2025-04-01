package com.github.card.usecases.purchasephysical;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.common.FindPurchaseByCodeGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@RequiredArgsConstructor
@ApplicationScoped
@Named("findPurchaseByCode")
public class FindPurchaseByCodeUseCase {

    private static final Logger log = LoggerFactory.getLogger(FindPurchaseByCodeUseCase.class);
    
    private final FindPurchaseByCodeGateway gateway;
    
    @Transactional(Transactional.TxType.SUPPORTS)
    public Purchase execute(String code) {
        log.info("Executing find purchase by code use case for: {}", code);
        return gateway.findByCode(code);
    }
}