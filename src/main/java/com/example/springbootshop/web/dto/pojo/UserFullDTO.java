package com.example.springbootshop.web.dto.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFullDTO {

    private Long id;

    private String name;

    private String login;

    private String password;

    private BigDecimal balance;

    private List<OrderFullDTO> orderFullDTOList;

    private Set<RoleFullDTO> roleFullDTOSet;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFullDTO that = (UserFullDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(balance, that.balance) && Objects.equals(orderFullDTOList, that.orderFullDTOList) && Objects.equals(roleFullDTOSet, that.roleFullDTOSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, balance, orderFullDTOList, roleFullDTOSet);
    }
}