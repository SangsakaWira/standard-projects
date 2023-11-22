package com.wira.sasangka.standardprojects.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wira.sasangka.standardprojects.feature.util.BaseResponse;
import com.wira.sasangka.standardprojects.feature.users.dto.JwtResponse;
import com.wira.sasangka.standardprojects.feature.users.dto.LoginRequest;
import com.wira.sasangka.standardprojects.feature.users.controller.AuthenticationController;
import com.wira.sasangka.standardprojects.feature.users.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.wira.sasangka.standardprojects.utils.WrapperObject.contentAsObject;
import static com.wira.sasangka.standardprojects.utils.WrapperObject.contentAsString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    AuthenticationController underTest;

    @InjectMocks
    AppUserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void loginSuccessWithResponse() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .username("user")
                .password("admin")
                .build();


        mockMvc.perform(
                post("/auth/login")
                        .contentType("application/json")
                        .content(contentAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            var data = JwtResponse.builder()
                    .userId("123")
                    .username("user")
                    .accessToken("token")
                    .expirationAccessToken(9000L)
                    .type("Bearer")
                    .build();

            TypeReference<BaseResponse<JwtResponse>> typeReference = new TypeReference<>() {};
            var content = contentAsObject(result.getResponse().getContentAsString(), typeReference);
            assertThat(content.data()).isEqualTo(data);
        });
    }
}