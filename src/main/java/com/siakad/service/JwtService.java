package com.siakad.service;

import com.siakad.model.dto.JwtComponent;

public interface JwtService {

    String generateToken(JwtComponent jwtComponent);

    JwtComponent extractToken(String token);
}
