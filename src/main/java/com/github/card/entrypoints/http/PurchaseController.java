package com.github.card.entrypoints.http;

import com.github.card.usecases.common.PurchaseUseCase;
import com.github.card.util.TypePurchaseConst;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Path("/api/v1/purchase")
public class PurchaseController {

    private final Map<String, PurchaseUseCase> purchaseUseCases;

    @POST
    @Path("/physical")
    @ResponseStatus(HttpStatus.SC_CREATED)
    public void create(@RequestBody @Valid PurchasePhysicalDTO dto) {
        var purchase = PurchasePhysicalMapper.INSTANCE.toEntity(dto);
        log.info("receive request {}", purchase);
        purchaseUseCases.get(TypePurchaseConst.PHYSICAL)
                .execute(purchase);
    }
}
