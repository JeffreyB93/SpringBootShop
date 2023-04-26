package com.example.springbootshop.service;

import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.dao.pojo.*;

public interface AuthService {

    JWTResponse authenticateUser(LoginRequest loginRequest);

    User registerUser(SignRequest signRequest);

    TokenRefreshResponse refreshToken(TokenRefreshRequest request);

    boolean logoutUser();
}