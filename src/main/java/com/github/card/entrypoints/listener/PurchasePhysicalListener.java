package com.github.card.entrypoints.listener;

import com.github.card.adapters.producers.PurchasePhysicalMessage;
import com.github.card.usecases.fraud.CheckRiskPurchaseUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import static com.github.card.util.ConvertObjetToJson.toObject;

@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
public class PurchasePhysicalListener {

    private final CheckRiskPurchaseUseCase checkRiskPurchaseUseCase;

    @Incoming("analyze")
    @Retry(delay = 10, maxRetries = 5)
    @Transactional(Transactional.TxType.REQUIRED)
    public void consume(String json) {
        log.info("message receive to analyse {}", json);
        final var obj = toObject(json, PurchasePhysicalMessage.class);

        checkRiskPurchaseUseCase.execute(obj.code());
    }
}
