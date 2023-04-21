package com.example.springbootshop.dao.pojo;

import com.example.springbootshop.dao.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignRequest {

    private Long id;

    private String name;

    private String login;

    private String password;

    private BigDecimal balance;

    private Set<Role> roleSet;
}