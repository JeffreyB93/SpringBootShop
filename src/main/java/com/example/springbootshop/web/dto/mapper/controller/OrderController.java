package com.example.springbootshop.web.dto.mapper.controller;

import com.example.springbootshop.dao.entity.Order;
import com.example.springbootshop.exception.ControllerException;
import com.example.springbootshop.exception.ServiceException;
import com.example.springbootshop.service.OrderService;
import com.example.springbootshop.web.dto.mapper.OrderFullMapper;
import com.example.springbootshop.web.dto.pojo.OrderFullDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderFullMapper orderFullMapper;
    private final OrderService orderService;

    public OrderController(OrderFullMapper orderFullMapper,
                           OrderService orderService) {
        this.orderFullMapper = orderFullMapper;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderFullDTO>> findAll() {
        List<Order> orderList = orderService.findAll();
        List<OrderFullDTO> orderFullDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderFullDTOList.add(orderFullMapper.convertFromEntity(order));
        }
        return ResponseEntity.ok(orderFullDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderFullDTO> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        OrderFullDTO orderFullDTO = orderFullMapper.convertFromEntity(order);
        return  ResponseEntity.ok(orderFullDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderFullDTO> update(@PathVariable Long id, @RequestBody Order order) throws ControllerException {
        Order orderUpdate = null;
        try {
            orderUpdate = orderService.update(id, order);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        OrderFullDTO orderUpdateFullDTO = orderFullMapper.convertFromEntity(orderUpdate);
        return ResponseEntity.ok(orderUpdateFullDTO);
    }

    @PostMapping
    public ResponseEntity<OrderFullDTO> save(@RequestBody Order order) throws ControllerException {
        try {
            Order orderSave = orderService.save(order);
            OrderFullDTO orderSaveFullDTO = orderFullMapper.convertFromEntity(orderSave);
            return new ResponseEntity<>(orderSaveFullDTO, HttpStatus.CREATED);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) throws ControllerException {
        try {
            orderService.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return id;
    }
}
