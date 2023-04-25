package com.example.springbootshop.dao.repository;

import com.example.springbootshop.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long> {
    Optional<Product> findByName(String name);
}