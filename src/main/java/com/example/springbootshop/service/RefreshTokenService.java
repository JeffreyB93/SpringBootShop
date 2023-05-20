package com.example.springbootshop.service;

import com.example.springbootshop.dao.entity.RefreshToken;
import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.dao.repository.RefreshTokenRepository;
import com.example.springbootshop.dao.repository.UserRepository;
import com.example.springbootshop.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        refreshToken.setExpireDate(LocalDateTime.now()
                .plusMinutes(refreshTokenDurationMinutes)
                .atZone(ZoneId.systemDefault()).toInstant());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new SecurityException("Время refresh token закончилось");
        }
        return refreshToken;
    }

    public int deleteByUserId(Long userId) {
        User user = userService.findById(userId);
        return refreshTokenRepository.deleteByUser(user);
    }
}