package com.github.card.exceptions.exception;

import com.github.card.exceptions.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.util.Collections;

public class SavePurchaseExceptionMapper implements ExceptionMapper<SavePurchaseException> {

    @Override
    public Response toResponse(SavePurchaseException e) {
        var error = new ErrorDTO(Response.Status.BAD_REQUEST.getStatusCode(), System.currentTimeMillis(), e.getMessage(), Collections.emptyList());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }
}
