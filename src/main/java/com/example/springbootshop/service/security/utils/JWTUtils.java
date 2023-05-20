package com.example.springbootshop.service.security.utils;

import com.example.springbootshop.service.security.UserDetailImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


@Component
public final class JWTUtils {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${access.jwt.expiration.minutes}")
    private int accessJwtExpirationMinutes;

    public String getUsernameFromJWTToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    
    public String generateTokenFromUsername(String username) {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        Date issueDate = Date.from(zonedDateTime.toInstant());
        Date exparedDate = Date.from(zonedDateTime.plusMinutes(accessJwtExpirationMinutes).toInstant());
        System.out.println(jwtSecret);
        return Jwts.builder().setSubject(username)
                .setIssuedAt(issueDate)
                .setExpiration(exparedDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateToken(UserDetailImpl userDetailImpl) {
        return generateTokenFromUsername(userDetailImpl.getUsername());
    }
}