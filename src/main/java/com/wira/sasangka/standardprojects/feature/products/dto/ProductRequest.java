package com.wira.sasangka.standardprojects.feature.products.dto;

import java.io.Serializable;

public record ProductRequest(
        String name,
        String description,
        Long qty,
        String price) implements Serializable {
}
