package com.siakad.service.impl;

import com.siakad.constant.GlobalMessage;
import com.siakad.entity.LogAuth;
import com.siakad.entity.MUser;
import com.siakad.exception.BusinessException;
import com.siakad.helper.StringHelper;
import com.siakad.model.dto.JwtComponent;
import com.siakad.model.request.LoginRequest;
import com.siakad.model.request.RefreshTokenRequest;
import com.siakad.model.response.LoginResponse;
import com.siakad.model.response.LogoutResponse;
import com.siakad.repository.LogAuthRepository;
import com.siakad.repository.UserRepository;
import com.siakad.service.AuthService;
import com.siakad.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final LogAuthRepository logAuthRepository;
    private static final long REFRESH_TOKEN_VALIDITY_IN_MONTH = 1L;

    @Transactional
    @Override
    public LoginResponse login(LoginRequest request) {
        MUser user = getUserByEmail(request.getEmail());
        verifyPassword(request.getPassword(), user.getPassword());

        String jwt = generateToken(user);
        String refreshToken = saveLogAuth(user);
        return buildLoginResponse(jwt, refreshToken);
    }

    @Override
    public LoginResponse loginWithRefreshToken(RefreshTokenRequest request) {
        LogAuth logAuth = findLogAuthByRefreshToken(request.getRefreshToken());
        String jwt = generateToken(logAuth.getUser());
        return buildLoginResponse(jwt, logAuth.getRefreshToken());

    }

    @Override
    public LogoutResponse logout(RefreshTokenRequest request) {
        LogAuth logAuth = findLogAuthByRefreshToken(request.getRefreshToken());
        logAuthRepository.delete(logAuth);
        return LogoutResponse.builder().refreshToken(logAuth.getRefreshToken()).build();
    }

    private MUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(GlobalMessage.WRONG_EMAIL_OR_PASSWORD));
    }

    private void verifyPassword(String rawPassword, String hashPassword) {
        boolean matches = passwordEncoder.matches(rawPassword, hashPassword);
        if (!matches) {
            throw new BusinessException(GlobalMessage.WRONG_EMAIL_OR_PASSWORD);
        }
    }

    private String generateToken(MUser user) {
        JwtComponent jwtComponent = JwtComponent.builder()
                .userId(user.getId().toString())
                .userEmail(user.getEmail())
                .userFullName(user.getFullName())
                .build();
        return jwtService.generateToken(jwtComponent);
    }

    private String saveLogAuth(MUser user) {
        LogAuth logAuth = LogAuth.builder()
                .refreshToken(generateRefreshToken())
                .refreshTokenExpiry(LocalDate.now().plusMonths(REFRESH_TOKEN_VALIDITY_IN_MONTH))
                .user(user)
                .build();
        logAuthRepository.save(logAuth);
        return logAuth.getRefreshToken();
    }

    private LoginResponse buildLoginResponse(String jwt, String refreshToken) {
        return LoginResponse.builder()
                .jwt(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    private LogAuth findLogAuthByRefreshToken(String refreshToken) {
        return logAuthRepository.findByRefreshTokenAndRefreshTokenExpiryAfter(refreshToken, LocalDate.now())
                .orElseThrow(() -> new BusinessException(GlobalMessage.REFRESH_TOKEN_NOT_VALID));
    }

    private String generateRefreshToken() {
        return String.format("%s%d", StringHelper.random(20), System.currentTimeMillis());
    }
}
