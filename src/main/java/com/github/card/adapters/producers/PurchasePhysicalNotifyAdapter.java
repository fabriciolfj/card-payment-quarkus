package com.github.card.adapters.producers;

import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.purchasephysical.PurchasePhysicalNotifyGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.card.util.ConvertObjetToJson.toJson;

@ApplicationScoped
public class PurchasePhysicalNotifyAdapter implements PurchasePhysicalNotifyGateway {

    private static final Logger log = LoggerFactory.getLogger(PurchasePhysicalNotifyAdapter.class);

    @Inject
    @Channel("analyze")
    private Emitter<String> analyseEmitter;

    @Override
    public void process(Purchase purchase) {
        var dto = new PurchasePhysicalMessage(purchase.code());

        analyseEmitter.send(toJson(dto))
                .whenComplete((v, t) -> {
                    if (t != null) {
                        log.error("fail send message to analyse, details: {}", t.getMessage());
                    }
                });
    }

}
