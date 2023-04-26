package com.example.springbootshop.web.config;


import com.example.springbootshop.service.security.UserDetailImpl;
import com.example.springbootshop.service.security.UserDetailServiceImpl;
import com.example.springbootshop.web.security.AuthEntryPointJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.security.auth.message.config.AuthConfig;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private AuthEntryPointJWT authEntryPointJWT;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    // TODO: 25.04.2023 Продолжить
}
