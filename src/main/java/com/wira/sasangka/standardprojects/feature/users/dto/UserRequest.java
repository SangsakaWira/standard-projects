package com.wira.sasangka.standardprojects.feature.users.dto;

import com.wira.sasangka.standardprojects.feature.users.entity.model.ERole;
import com.wira.sasangka.standardprojects.feature.users.entity.model.AppUser;
import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link AppUser}
 */
public record UserRequest(
        @NotNull(message = "Name can't be blank")
        @NotEmpty(message = "Name can't be blank")
        @NotBlank(message = "Name can't be blank")
        String name,
        @NotNull(message = "Password can't be blank")
        @NotEmpty(message = "Password can't be blank")
        @NotBlank(message = "Password can't be blank")
        String password,
        @NotNull(message = "Email can't be blank")
        @Email(message = "Email is not valid")
        @NotEmpty(message = "Email can't be blank")
        @NotBlank(message = "Email can't be blank")
        String email,
        @NotNull(message = "Username can't be blank")
        @NotEmpty(message = "Username can't be blank")
        @NotBlank(message = "Username can't be blank")
        String username,
        @NotNull(message = "Phone number can't be blank")
        @NotEmpty(message = "Phone number can't be blank")
        @NotBlank(message = "Phone number can't be blank")
        String phoneNumber,
        ERole role) implements Serializable {
}