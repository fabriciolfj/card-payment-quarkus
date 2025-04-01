package com.github.card.entrypoints.http;

import com.github.card.usecases.common.PurchaseUseCase;
import com.github.card.usecases.purchasephysical.FindPurchaseByCodeUseCase;
import com.github.card.util.TypePurchaseConst;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RequiredArgsConstructor
@Path("/api/v1/purchase")
public class PurchaseController {

    private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);

    private final Map<String, PurchaseUseCase> purchaseUseCases;
    private final FindPurchaseByCodeUseCase findPurchaseByCodeUseCase;

    @POST
    @Path("/physical")
    @ResponseStatus(HttpStatus.SC_CREATED)
    public void create(@RequestBody @Valid PurchasePhysicalDTO dto) {
        var purchase = PurchasePhysicalMapper.INSTANCE.toEntity(dto);
        log.info("receive request {}", purchase);
        purchaseUseCases.get(TypePurchaseConst.PHYSICAL)
                .execute(purchase);
    }
    
    @GET
    @Path("/{code}")
    @ResponseStatus(HttpStatus.SC_OK)
    @Produces(MediaType.APPLICATION_JSON)
    public PurchaseResponseDTO getPurchase(@PathParam("code") final String code) {
        log.info("Received request to get purchase with code: {}", code);
        var purchase = findPurchaseByCodeUseCase.execute(code);
        return PurchaseResponseMapper.INSTANCE.toDto(purchase);
    }
}
