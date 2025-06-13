package com.siakad.service;

import com.siakad.model.request.LoginRequest;
import com.siakad.model.request.RefreshTokenRequest;
import com.siakad.model.response.LoginResponse;
import com.siakad.model.response.LogoutResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse loginWithRefreshToken(RefreshTokenRequest request);

    LogoutResponse logout(RefreshTokenRequest request);
}

