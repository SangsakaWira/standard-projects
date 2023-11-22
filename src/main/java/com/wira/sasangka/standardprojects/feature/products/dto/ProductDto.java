package com.wira.sasangka.standardprojects.feature.products.dto;

import com.wira.sasangka.standardprojects.feature.products.entity.model.Product;
import lombok.Builder;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Product}
 */
@Builder
public record ProductDto(
        String id,
        Instant createdAt,
        Instant updatedAt,
        String name,
        String description,
        Long qty,
        String price) implements Serializable {
}