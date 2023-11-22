/*
 * Copyright (c) 2022.
 */

package com.wira.sasangka.standardprojects.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wira.sasangka.standardprojects.feature.util.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        BaseResponse<Void> failureResponse = BaseResponse.<Void>builder()
                .responseCode(response.getStatus())
                .responseMessage(authException.getMessage())
                .build();

        log.error("Unauthorized error: {}", authException.getMessage());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), failureResponse);
    }
}
