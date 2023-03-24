package com.example.springbootshop.service;

import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.exception.ServiceException;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByLogin(String login);

    User update(Long id, User user) throws ServiceException;

    User save(User user) throws ServiceException;

    Long delete(Long id) throws ServiceException;
}