package com.wira.sasangka.standardprojects.feature.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password) implements Serializable {
}
