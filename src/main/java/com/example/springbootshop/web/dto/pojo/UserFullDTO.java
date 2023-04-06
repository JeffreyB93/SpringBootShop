package com.example.springbootshop.web.dto.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
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
    // TODO: 28.03.2023 добавить анатации, добавить по логике
    private Long id;

    //@NotBlank - не пустой
    //@NotEmpty - не пустой для колекция
    @NotNull(message = "Имя нет")
    @Pattern(regexp = "^[a-zA-Z\sа-яА-Я]*$", message = "Должны быть только буквы! ${validatedValue}")
    private String name;

    //@Max(value = 10)//бываюь на макс и минимальное значение
    //@Min(value = 0)
    //@Positive// все числа не отрицательные
    //@Email
    @NotNull
    @Email
    private String login;

    private String password;
    @NotNull
    @Positive
    private BigDecimal balance;

    private List<OrderFullDTO> orderFullDTOList;

    private Set<RoleFullDTO> roleFullDTOSet;

//    @JsonIgnore
//    public String getPassword() {
//        return password;
//    }

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