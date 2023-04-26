package com.example.springbootshop.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Table(name = "refresh_token")
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expire_date")
    private Instant expireDate;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;
}