package com.example.springbootshop.web.dto.mapper.controller;

import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.dao.pojo.*;
import com.example.springbootshop.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //@Autowired
    //private SecurityFullMapper securityFullMapper;

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticateUser")
    public ResponseEntity<JWTResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JWTResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody SignRequest signRequest) {
        User user = authService.registerUser(signRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        TokenRefreshResponse tokenRefreshResponse = authService.refreshToken(request);
        return ResponseEntity.ok(tokenRefreshResponse);
    }

    @GetMapping
    public ResponseEntity<Boolean> logoutUser() {
        return ResponseEntity.ok(authService.logoutUser());
    }
}