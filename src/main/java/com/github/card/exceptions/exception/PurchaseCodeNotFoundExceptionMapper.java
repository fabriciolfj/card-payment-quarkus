package com.github.card.exceptions.exception;

import com.github.card.exceptions.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

@Provider
public class PurchaseCodeNotFoundExceptionMapper implements ExceptionMapper<PurchaseCodeNotFoundException> {

    @Override
    public Response toResponse(PurchaseCodeNotFoundException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ErrorDTO(Response.Status.NOT_FOUND.getStatusCode(),
                        LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                        exception.getMessage(),
                        Collections.emptyList()))
                .build();
    }
}