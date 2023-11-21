package com.wira.sasangka.standardprojects.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password) implements Serializable {
}
