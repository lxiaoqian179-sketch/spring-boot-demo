package com.example.demo.repository;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderRepository {

    private final OrderMapper orderMapper;

    public OrderRepository(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    public List<Order> findByUserId(Integer userId) {
        return orderMapper.findByUserId(userId);
    }

    public List<Order> findByStatus(String status) {
        return orderMapper.findByStatus(status);
    }
}