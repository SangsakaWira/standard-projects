package com.wira.sasangka.standardprojects.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wira.sasangka.standardprojects.dto.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AuthorizationFilterDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        BaseResponse<Void> failureResponse = BaseResponse.<Void>builder()
                .responseCode(response.getStatus())
                .responseMessage(accessDeniedException.getMessage())
                .build();

        log.error("Access denied: {}", accessDeniedException.getMessage());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), failureResponse);
    }
}
