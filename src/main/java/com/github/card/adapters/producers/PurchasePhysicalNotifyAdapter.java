package com.github.card.adapters.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.purchasephysical.PurchasePhysicalNotifyGateway;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class PurchasePhysicalNotifyAdapter implements PurchasePhysicalNotifyGateway {

    @Inject
    @Channel("analyze")
    private Emitter<String> analyseEmitter;

    @Inject
    private ObjectMapper mapper;

    @SneakyThrows
    @Override
    public void process(Purchase purchase) {
        var dto = new PurchasePhysicalMessage(purchase.code());

        analyseEmitter.send(mapper.writeValueAsString(dto))
                .whenComplete((v, t) -> {
                    if (t != null) {
                        log.error("fail send message to analyse, details: {}", t.getMessage());
                    }
                });
    }
}
