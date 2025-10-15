package com.dpp.ddp_study_management.service.impl;

import com.dpp.ddp_study_management.common.util.JwtUtil;
import com.dpp.ddp_study_management.dto.request.user.LoginRequest;
import com.dpp.ddp_study_management.dto.response.user.TokenResponse;
import com.dpp.ddp_study_management.service.AuthService;
import com.dpp.ddp_study_management.service.BlacklistedTokenService;
import com.dpp.ddp_study_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final BlacklistedTokenService blacklistedTokenService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        userService.findByUsername(loginRequest.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        return new TokenResponse(jwtUtil.generateToken(loginRequest.getUsername()));
    }

    @Override
    public void logout(String token) {
        blacklistedTokenService.addBlacklistedToken(token);
    }
}