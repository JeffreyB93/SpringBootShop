package com.example.springbootshop.web.dto.pojo;

import com.example.springbootshop.dao.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@Data
public class UserWithoutOrderListDTO {

    private Long id;

    @NotNull(message = "Имя нет")
    @Pattern(regexp = "^[a-zA-Z\sа-яА-Я]*$", message = "Должны быть только буквы! ${validatedValue}")
    private String name;

    @NotNull
    @Email
    private String login;

    @NotNull
    private String password;

    @NotNull
    @Positive
    private BigDecimal balance;

    private Set<RoleFullDTO> roleFullDTOSet;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWithoutOrderListDTO that = (UserWithoutOrderListDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(balance, that.balance) && Objects.equals(roleFullDTOSet, that.roleFullDTOSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, balance, roleFullDTOSet);
    }
}
