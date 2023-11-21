package com.wira.sasangka.standardprojects.service;

import com.wira.sasangka.standardprojects.dto.BaseResponse;
import com.wira.sasangka.standardprojects.dto.PasswordRequest;
import com.wira.sasangka.standardprojects.dto.UserRequest;
import com.wira.sasangka.standardprojects.entity.AppUser;
import com.wira.sasangka.standardprojects.exceptions.AppRuntimeException;
import com.wira.sasangka.standardprojects.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.wira.sasangka.standardprojects.constant.AppConstants.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.getAppUserByUsername(username);
    }

    @Override
    public BaseResponse<Void> createNewUser(UserRequest request) {
        AppUser user = new AppUser();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        user.setPhoneNumber(request.phoneNumber());
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);

        userRepository.save(user);

        return BaseResponse.<Void>builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage("Success to register")
                .build();
    }

    @Override
    public BaseResponse<Void> resetPassword(String id, PasswordRequest request) {
        AppUser appUser = getAppUserById(id);

        boolean matches = passwordEncoder.matches(request.oldPassword(), appUser.getPassword());
        if (!matches) {
            throw new AppRuntimeException("Password did not match");
        }

        boolean passwordNewMatchWithOldPassword = passwordEncoder.matches(
                request.newPassword(),
                appUser.getPassword()
        );

        if (passwordNewMatchWithOldPassword) {
            throw new AppRuntimeException("Please enter another password!");
        }

        appUser.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(appUser);

        return BaseResponse.<Void>builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage("Success to change your password")
                .build();
    }

    private AppUser getAppUserById(String userId) {
        String message = messageFormat(USER_NOT_FOUND_MSG, userId);

        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(message));
    }

    private AppUser getAppUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(EMAIL_OR_USERNAME_NOT_PROVIDED_MSG));
    }
}
