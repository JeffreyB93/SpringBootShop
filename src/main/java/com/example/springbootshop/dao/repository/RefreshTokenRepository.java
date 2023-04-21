package com.example.springbootshop.dao.repository;

import com.example.springbootshop.dao.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
