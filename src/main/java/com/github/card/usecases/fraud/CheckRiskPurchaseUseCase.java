package com.github.card.usecases.fraud;

import com.github.card.entities.common.Status;
import com.github.card.usecases.purchasephysical.FindPurchaseByCodeUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CheckRiskPurchaseUseCase {

    private final static int GREAT = 1;
    private final static int AVERAGE = 0;
    private final static long DEFAULT_VALUE = 0L;

    private final List<ValidationRiskUseCase> riskUseCases;
    private final FindPurchaseByCodeUseCase findPurchaseByCodeUseCase;
    private final UpdateStatusPurchaseGateway updateStatusPurchaseGateway;

    //TODO mudar para sistema de pontuacao
    public void execute(final String code) {
        var purchase = findPurchaseByCodeUseCase.execute(code);

        var result = riskUseCases.stream()
                .map(exc -> exc.execute(purchase))
                .toList()
                .stream().collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting())
                );

        var purchaseUpdated = switch (getLevelRisk(result)) {
            case GREAT -> purchase.updateStatus(Status.VALID);
            case AVERAGE -> purchase.updateStatus(Status.ASSESSMENT);
            default -> purchase.updateStatus(Status.DENIED);
        };

        log.info("evaluated purchase {}-{}", purchaseUpdated.code(), purchaseUpdated.status());
        updateStatusPurchaseGateway.process(purchaseUpdated);
    }

    private static int getLevelRisk(final Map<Boolean, Long> risk) {
        var result = risk.getOrDefault(true, DEFAULT_VALUE)
                .compareTo(risk.getOrDefault(false, DEFAULT_VALUE)
        );

        log.info("result level risk {}", result);
        return result;
    }
}
