package com.github.card.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorDTO(int status, long timestamp, String message, List<DetailsErrorDTO> errors) { }
