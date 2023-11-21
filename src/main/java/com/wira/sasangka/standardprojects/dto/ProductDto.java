package com.wira.sasangka.standardprojects.dto;

import lombok.Builder;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.wira.sasangka.standardprojects.entity.Product}
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