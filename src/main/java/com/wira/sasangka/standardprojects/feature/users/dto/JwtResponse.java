package com.wira.sasangka.standardprojects.feature.users.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record JwtResponse(
        String userId,
        String username,
        String accessToken,
        Long expirationAccessToken,
        String type) implements Serializable {
}
