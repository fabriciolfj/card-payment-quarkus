package com.github.card.entrypoints;

import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.ResponseStatus;

@Slf4j
@Path("/api/v1/cards")
public class CardController {

    @POST
    @ResponseStatus(HttpStatus.SC_CREATED)
    public void create(@RequestBody @Valid CardDTO card) {
        log.info("receive request {}", card);
    }
}
