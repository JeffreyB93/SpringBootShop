package com.example.springbootshop.service.impl;

import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.dao.repository.UserRepository;
import com.example.springbootshop.exception.ServiceException;
import com.example.springbootshop.service.UserService;
import com.example.springbootshop.service.validator.BaseValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BaseValidator baseValidator;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BaseValidator baseValidator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.baseValidator = baseValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(RuntimeException::new);
        return user;
    }

    @Override
    public User findByLogin(String login) {
        Optional<User> userOptional = userRepository.findByLogin(login);
        User user = userOptional.orElseThrow(RuntimeException::new);
        return user;
    }

    @Override
    public User update(Long id, User user) throws ServiceException {
        if (!userRepository.existsById(id)){
            throw new ServiceException("Такого пользователя нет!");
        }
        user.setId(id);
        User updateUser = userRepository.saveAndFlush(user);
        return updateUser;
    }


    @Override
    public User save(User user) throws ServiceException {
        if (user.getId() != null) {
            throw new ServiceException("У данного пользователя есть id!");
        }// TODO: 28.03.2023 Посиотреть matches
        if (baseValidator.isValidPassword(user.getPassword()) && baseValidator.isValidBalance(user.getBalance()) && user.getName().matches("^[a-zA-Z\sа-яА-Я]*$")) {
            User saveUser = userRepository.saveAndFlush(user);
            return saveUser;
        }
        throw new ServiceException("не правильный isValidPassword или isValidBalance");
    }

    @Override
    public Long delete(Long id) throws ServiceException {
        if (!userRepository.existsById(id)) {
            throw new ServiceException("Такого пользователя нет!");
        }
        userRepository.deleteById(id);
        return id;
    }
}