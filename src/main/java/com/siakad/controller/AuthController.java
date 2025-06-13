package com.siakad.controller;

import com.siakad.constant.Endpoint;
import com.siakad.model.request.LoginRequest;
import com.siakad.model.request.RefreshTokenRequest;
import com.siakad.model.response.LoginResponse;
import com.siakad.model.response.LogoutResponse;
import com.siakad.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    public LoginResponse loginWithRefreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return authService.loginWithRefreshToken(request);
    }

    @PostMapping("/logout")
    public LogoutResponse logout(@RequestBody @Valid RefreshTokenRequest request) {
        return authService.logout(request);
    }
}
