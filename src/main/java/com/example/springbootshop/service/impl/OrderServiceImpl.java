package com.example.springbootshop.service.impl;

import com.example.springbootshop.dao.entity.Order;

import com.example.springbootshop.dao.repository.OrderRepository;
import com.example.springbootshop.exception.ServiceException;
import com.example.springbootshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        Order order = orderOptional.orElseThrow(RuntimeException::new);
        return order;
    }

    @Override
    public Order update(Long id, Order order) throws ServiceException {
        if (!orderRepository.existsById(id)) {
            throw new ServiceException("Такого заказа нет!");
        }
        order.setId(id);
        Order updateOrder = orderRepository.saveAndFlush(order);
        return updateOrder;
    }

    @Override
    public Order save(Order order) throws ServiceException {
        if (order.getId() != null) {
            throw new ServiceException("У данного заказа есть id!");
        }
        Order saveOrder = orderRepository.saveAndFlush(order);
        return saveOrder;
    }

    @Override
    public Long delete(Long id) throws ServiceException {
        if (!orderRepository.existsById(id)) {
            throw new ServiceException("Такого заказа нет!");
        }
        orderRepository.deleteById(id);
        return id;
    }
}