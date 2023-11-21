package com.wira.sasangka.standardprojects.controller;

import com.wira.sasangka.standardprojects.dto.*;
import com.wira.sasangka.standardprojects.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AppUserService userService;

    @PostMapping(
            path = "/signin",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<JwtResponse>> login(@RequestBody LoginRequest ignoredLoginRequest) {
        BaseResponse<JwtResponse> response = BaseResponse
                .<JwtResponse>builder()
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<Void>> register(@Valid @RequestBody UserRequest request) {
        BaseResponse<Void> response = userService.createNewUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(
            path = "/reset-password",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<Void>> resetPassword(@RequestParam("user-id") String id,
                                                            @RequestBody PasswordRequest request) {
        BaseResponse<Void> response = userService.resetPassword(id, request);
        return ResponseEntity.ok(response);
    }
}
