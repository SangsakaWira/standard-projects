package com.wira.sasangka.standardprojects.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wira.sasangka.standardprojects.dto.BaseResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AppLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();

        if (authentication == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());

            BaseResponse<Void> failureResponse = BaseResponse.<Void>builder()
                    .responseCode(response.getStatus())
                    .responseMessage("You're not logged in")
                    .build();
            objectMapper.writeValue(response.getOutputStream(), failureResponse);
            return;
        }

        log.info("User logged out: " + authentication.getName());

        BaseResponse<Void> baseResponse = BaseResponse.<Void>builder()
                .responseCode(200)
                .responseMessage("Successfully logged out")
                .build();
        objectMapper.writeValue(response.getOutputStream(), baseResponse);
    }
}
