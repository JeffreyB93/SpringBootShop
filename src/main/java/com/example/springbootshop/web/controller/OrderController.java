package com.example.springbootshop.web.controller;



import com.example.springbootshop.dao.entity.Order;
import com.example.springbootshop.exception.ControllerException;
import com.example.springbootshop.exception.ServiceException;
import com.example.springbootshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findAll() {
        List<Order> orderList = orderService.findAll();
        return orderList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return  ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) throws ControllerException {
        Order orderUpdate = null;
        try {
            orderUpdate = orderService.update(id, order);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return ResponseEntity.ok(orderUpdate);
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) throws ControllerException {
        try {
            Order orderSave = orderService.save(order);
            return new ResponseEntity<>(orderSave, HttpStatus.CREATED);
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
