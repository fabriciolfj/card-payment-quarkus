package com.github.card.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertObjetToJson {

    private static ObjectMapper OBJECT_MAPPER;

    public static <T> String toJson(T value) {
        try {
            var mapper = getObjectMapper();
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static ObjectMapper getObjectMapper() {
        if (OBJECT_MAPPER == null) {
            OBJECT_MAPPER = new ObjectMapper();
        }

        return OBJECT_MAPPER;
    }


}
