package com.example.springbootshop.service.impl;

import com.example.springbootshop.dao.entity.RefreshToken;
import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.dao.pojo.*;
import com.example.springbootshop.dao.repository.RoleRepository;
import com.example.springbootshop.dao.repository.UserRepository;
import com.example.springbootshop.service.AuthService;
import com.example.springbootshop.service.RefreshTokenService;
import com.example.springbootshop.service.security.UserDetailImpl;
import com.example.springbootshop.service.security.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JWTUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JWTUtils jwtUtils, RefreshTokenService refreshTokenService, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

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
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetail.getId());
        return new JWTResponse(jwt, refreshToken.getToken(), userDetail.getId(), userDetail.getUsername(),
                userDetail.getUsername(), roleSet);
    }

    @Override
    public User registerUser(SignRequest signRequest) {
        if (userRepository.findByLogin(signRequest.getLogin()).isPresent()) {
            throw new SecurityException("Такой логин есть");
        }
        User user = User.builder()
                .name(signRequest.getName())
                .login(signRequest.getLogin())
                .balance(signRequest.getBalance())
                .password(passwordEncoder.encode(signRequest.getPassword()))
                .roleSet(Collections.singleton(roleRepository.findByName("ROLE_USER")))
                .build();
        return userRepository.save(user);
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        TokenRefreshResponse tokenRefreshResponse = refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getLogin());
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new SecurityException("Вы ввели не верный рефрештокен"));
        return tokenRefreshResponse;
    }

    @Override
    public boolean logoutUser() {
        UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return refreshTokenService.deleteByUserId(userDetail.getId()) > 0;
    }
}