package com.example.springbootshop.service;

import com.example.springbootshop.dao.entity.Order;
import com.example.springbootshop.exception.ServiceException;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(Long id);

    Order update(Long id, Order order) throws ServiceException;

    Order save(Order order) throws ServiceException;

    Long delete(Long id) throws ServiceException;
}
