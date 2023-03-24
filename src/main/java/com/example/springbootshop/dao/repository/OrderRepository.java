package com.example.springbootshop.dao.repository;

import com.example.springbootshop.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Long> {
}
