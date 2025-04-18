package com.github.card.exceptions.exception;

import com.github.card.exceptions.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.http.HttpStatus;

import java.util.Collections;

@Provider
public class PurchaseCodeNotFoundExceptionMapper implements ExceptionMapper<PurchaseCodeNotFoundException> {

    @Override
    public Response toResponse(PurchaseCodeNotFoundException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ErrorDTO(HttpStatus.SC_BAD_REQUEST,
                        System.currentTimeMillis(),
                        exception.getMessage(),
                        Collections.emptyList()))
                .build();
    }
}