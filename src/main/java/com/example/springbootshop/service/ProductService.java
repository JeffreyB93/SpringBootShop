package com.example.springbootshop.service;

import com.example.springbootshop.dao.entity.Product;
import com.example.springbootshop.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    Product findByName(String name);

    Product update(Long id, Product product) throws ServiceException;

    Product save(Product product) throws  ServiceException;

    Long delete(Long id) throws ServiceException;
}