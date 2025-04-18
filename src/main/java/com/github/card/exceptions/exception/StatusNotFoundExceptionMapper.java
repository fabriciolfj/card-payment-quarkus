package com.github.card.exceptions.exception;

import com.github.card.exceptions.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.apache.http.HttpStatus;
import java.util.Collections;

public class StatusNotFoundExceptionMapper implements ExceptionMapper<StatusNotFoundException> {

    @Override
    public Response toResponse(StatusNotFoundException e) {
        var dto = new ErrorDTO(HttpStatus.SC_BAD_REQUEST,
                System.currentTimeMillis(),
                e.getMessage(),
                Collections.emptyList());

        return Response.status(HttpStatus.SC_BAD_REQUEST)
                .entity(dto)
                .build();
    }
}
