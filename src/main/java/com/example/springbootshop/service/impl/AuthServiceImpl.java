package com.example.springbootshop.service.impl;

import com.example.springbootshop.dao.entity.RefreshToken;
import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.dao.pojo.*;
import com.example.springbootshop.dao.repository.UserRepository;
import com.example.springbootshop.service.AuthService;
import com.example.springbootshop.service.security.UserDetailImpl;
import com.example.springbootshop.service.security.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public JWTResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetail);
        Set<String> roleSet = userDetail.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        RefreshToken refreshToken = re
        return null;
    }
    // TODO: 25.04.2023 Продолжить
    @Override
    public User registerUser(SignRequest signRequest) {
        return null;
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        return null;
    }

    @Override
    public boolean logoutUser() {
        return false;
    }
}
