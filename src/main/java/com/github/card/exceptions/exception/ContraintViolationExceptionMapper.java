package com.github.card.exceptions.exception;

import com.github.card.exceptions.dto.DetailsErrorDTO;
import com.github.card.exceptions.dto.ErrorDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.*;
import java.util.stream.Collectors;

@Provider
public class ContraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        var errors = violations.stream().map(violation -> {
            final String propertyPath = violation.getPropertyPath().toString();
            final String field = propertyPath.contains(".")
                    ? propertyPath.substring(propertyPath.lastIndexOf('.') + 1)
                    : propertyPath;

            return new DetailsErrorDTO(field, violation.getMessage());
        }).toList();

        var dto = new ErrorDTO(Response.Status.BAD_REQUEST.getStatusCode(),
                System.currentTimeMillis(),
                "fail request",
                errors);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(dto)
                .build();
    }
}
