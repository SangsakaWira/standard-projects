package com.wira.sasangka.standardprojects.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WrapperObject {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String contentAsString(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T contentAsObject(String content, TypeReference<T> reference) {
        try {
            return MAPPER.readValue(content, reference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
