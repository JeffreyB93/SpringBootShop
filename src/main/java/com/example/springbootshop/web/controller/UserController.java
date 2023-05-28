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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
@Validated
public class UserController {

    private final UserFullMapper userFullMapper;
    private final UserService userService;

    public UserController(UserFullMapper userFullMapper,
                          UserService userService) {
        this.userFullMapper = userFullMapper;
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        List<UserFullDTO> userFullDTOList = new ArrayList<>();
        for (User user : userList) {
            userFullDTOList.add(userFullMapper.convertFromEntity(user));
        }
        model.addAttribute("userList", userFullDTOList);
        model.addAttribute("user", new User());
        return "users/userPage";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFullDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserFullDTO userFullDTO = userFullMapper.convertFromEntity(user);
        return ResponseEntity.ok(userFullDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<UserFullDTO> findByLogin(@RequestParam(name = "login") String log) {
        User user = userService.findByLogin(log);
        UserFullDTO userFullDTO = userFullMapper.convertFromEntity(user);
        return ResponseEntity.ok(userFullDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserFullDTO> update(@PathVariable Long id, @RequestBody UserFullDTO userFullDTO) throws ControllerException {
        User userUpdate = null;
        try {
            userUpdate = userService.update(id, userFullMapper.convertFromDTO(userFullDTO));
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        UserFullDTO userUpdateFullDTO = userFullMapper.convertFromEntity(userUpdate);
        return ResponseEntity.ok(userUpdateFullDTO);
    }

    @PostMapping
    public String save(@ModelAttribute("user") User user) {

        System.out.println(user.toString());
        return "redirect:/";
        /*try {
            User userSave = userService.save(userFullMapper.convertFromDTO(userFullDTO));
            UserFullDTO userSaveFullDTO = userFullMapper.convertFromEntity(userSave);
            return new ResponseEntity<>(userSaveFullDTO, HttpStatus.CREATED);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }*/
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