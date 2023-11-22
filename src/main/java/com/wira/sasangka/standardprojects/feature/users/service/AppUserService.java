package com.wira.sasangka.standardprojects.feature.users.service;

import com.wira.sasangka.standardprojects.feature.util.BaseResponse;
import com.wira.sasangka.standardprojects.feature.users.dto.PasswordRequest;
import com.wira.sasangka.standardprojects.feature.users.dto.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserService extends UserDetailsService {
    @Transactional
    BaseResponse<Void> createNewUser(UserRequest request);

    @Transactional
    BaseResponse<Void> resetPassword(String id, PasswordRequest request);
}
