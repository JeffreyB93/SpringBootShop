package com.example.springbootshop.dao.pojo;

import com.example.springbootshop.dao.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JWTResponse {

    private String token;

    private String type = "Bearer";

    private String refreshToken;

    private Long id;

    private String username;

    private String login;

    private Set<Role> roleSet;

    public JWTResponse(String token, String refreshToken, Long id, String username, String login, Set<Role> roleSet) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.login = login;
        this.roleSet = roleSet;
    }
}