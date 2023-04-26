package com.example.springbootshop.service;

import com.example.springbootshop.dao.entity.RefreshToken;
import com.example.springbootshop.dao.repository.RefreshTokenRepository;
import com.example.springbootshop.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private UserService userService;

    @Value("${refresh.token.duration.minutes}")
    private int refreshTokenDurationMinutes;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userService.findById(userId));
        refreshToken.setExpireDate(LocalDateTime.now().plusMinutes(refreshTokenDurationMinutes)
                .atZone(ZoneId.systemDefault()).toInstant());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
    // TODO: 25.04.2023 Продолжить

}