package com.github.card.entrypoints.listener;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class PurchasePhysicalListener {

    private static final Logger log = LoggerFactory.getLogger(PurchasePhysicalListener.class);

    @Incoming("analyze-out")
    @Retry(delay = 10, maxRetries = 5)
    public void consume(String json) {
        log.info("message receive to analyse {}", json);
    }
}
