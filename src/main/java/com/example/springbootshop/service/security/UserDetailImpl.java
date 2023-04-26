package com.example.springbootshop.service.security;

import com.example.springbootshop.dao.entity.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailImpl implements UserDetails {

    private Long id;

    private String login;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(Long id, String login, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailImpl build(User user) {
        List<GrantedAuthority> authorityList = user.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new UserDetailImpl(user.getId(), user.getLogin(), user.getPassword(), authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailImpl that = (UserDetailImpl) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(authorities, that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, authorities);
    }
}