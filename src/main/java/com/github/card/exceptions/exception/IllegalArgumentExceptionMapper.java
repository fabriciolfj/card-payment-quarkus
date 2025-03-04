package com.github.card.exceptions.exception;

import com.github.card.exceptions.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Collections;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(final IllegalArgumentException e) {
        var error = new ErrorDTO(Response.Status.BAD_REQUEST.getStatusCode(), System.currentTimeMillis(), e.getMessage(), Collections.emptyList());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }
}
