package com.example.springbootshop.web.controller;

import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.exception.ControllerException;
import com.example.springbootshop.exception.ServiceException;
import com.example.springbootshop.service.UserService;

import com.example.springbootshop.web.dto.mapper.UserFullMapper;
import com.example.springbootshop.web.dto.pojo.UserFullDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserFullMapper userFullMapper;

    private final UserService userService;

    public UserController(UserFullMapper userFullMapper,
                          UserService userService) {
        this.userFullMapper = userFullMapper;
        this.userService = userService;
    }

    // TODO: 24.03.2023  добавить дто во всех контролерах
    @GetMapping
    public ResponseEntity<List<UserFullDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserFullDTO> userFullDTOList = new ArrayList<>();
        for (User user : userList) {
            userFullDTOList.add(userFullMapper.convertFromEntity(user));
        }
        return ResponseEntity.ok(userFullDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/login")
    public ResponseEntity<User> findByLogin(@RequestParam(name = "login") String log) {
        User user = userService.findByLogin(log);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) throws ControllerException {
        User userUpdate = null;
        try {
            userUpdate = userService.update(id, user);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return ResponseEntity.ok(userUpdate);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) throws ControllerException {
        try {
            User userSave = userService.save(user);
            return new ResponseEntity<>(userSave, HttpStatus.CREATED);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) throws ControllerException {
        try {
            userService.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return id;
    }
}