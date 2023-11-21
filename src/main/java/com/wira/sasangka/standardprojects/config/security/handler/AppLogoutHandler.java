package com.wira.sasangka.standardprojects.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.JWTClaimsSet;
import com.wira.sasangka.standardprojects.dto.BaseResponse;
import com.wira.sasangka.standardprojects.config.security.SecurityUtils;
import com.wira.sasangka.standardprojects.config.security.jwt.JwtService;
import com.wira.sasangka.standardprojects.exceptions.AppRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Component
@Slf4j
public class AppLogoutHandler implements LogoutHandler {
    private final JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        log.info("Logout request");
        log.info(authentication.toString());
        authentication = SecurityUtils.getAuthentication();

        String accessToken = jwtService.getJwtTokenFromHeader(request);
        if (accessToken == null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                BaseResponse<Void> failureResponse = BaseResponse.<Void>builder()
                        .responseCode(response.getStatus())
                        .responseMessage("Full authentication is required to access this resource")
                        .build();

                mapper.writeValue(response.getOutputStream(), failureResponse);
            } catch (IOException e) {
                throw new AppRuntimeException(e);
            }
        }

        log.debug("Authentication: {}", authentication);

        if (authentication == null) {
            return;
        }

        log.info("User logged out: " + authentication.getName());

        JWTClaimsSet jwtClaimsSet = jwtService.parseToken(accessToken);
        String username = jwtClaimsSet.getSubject();
        log.info("User from token: " + username);

        if (authentication.getName().equals(username)) {
            SecurityUtils.clearAuthentication();
            SecurityContextHolder.clearContext();
        }
    }
}
