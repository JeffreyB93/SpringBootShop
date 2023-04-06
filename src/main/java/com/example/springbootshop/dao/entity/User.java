package com.example.springbootshop.dao.entity;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "demo_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String login;

    private String password;

    private BigDecimal balance;

    //fetch - вытягивание данных, cascade - связанно с удаление
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Order> orderList;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(balance, user.balance) && Objects.equals(orderList, user.orderList) && Objects.equals(roleSet, user.roleSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, balance, orderList, roleSet);
    }
}