package com.github.card.adapters.purchase;

import com.github.card.adapters.repositories.purchase.data.PurchaseData;
import com.github.card.adapters.repositories.purchase.repository.PurchaseRepository;
import com.github.card.entities.common.Status;
import com.github.card.entities.physical.Purchase;
import com.github.card.usecases.fraud.UpdateStatusPurchaseGateway;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.card.adapters.purchase.PurchaseDataMapper.enrichStatusData;


@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
public class UpdateStatusPurchaseAdapter implements UpdateStatusPurchaseGateway  {

    private final PurchaseRepository purchaseRepository;

    @Override
    public void process(final Purchase purchase) {
        var data = purchaseRepository.findByCode(purchase.code());

        data.ifPresentOrElse( value -> {
                    var valueEnchied = enrichStatusData(value, getStatusToPersist(purchase, value));
                    log.info("quantity new status init persist {}", valueEnchied.getStatus().size());

                    purchaseRepository.executeSave(valueEnchied);
                },
                ()  -> log.error("purchase not found {}, impossibility updated status", purchase.code())
        );
    }

    private List<Status> getStatusToPersist(final Purchase purchase, final PurchaseData data) {
        var statusData = new HashSet<>(data.getStatusDescribe());

        return purchase.status()
                .stream()
                .filter(v -> !statusData.contains(v.getStatusDescribe()))
                .toList();
    }
}
