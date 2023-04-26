package com.example.springbootshop.dao.repository;

import com.example.springbootshop.dao.entity.RefreshToken;
import com.example.springbootshop.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}