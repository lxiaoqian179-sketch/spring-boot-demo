package com.example.demo.mapper;

import com.example.demo.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> findAll();
    List<Order> findByUserId(Integer userId);

    List<Order> findByStatus(@Param("status") String status);
}