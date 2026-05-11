package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        return ResponseEntity.ok(ApiResponse.success(orderService.getAllOrders()));
    }

    @GetMapping("/users/{id}/orders")
    public ResponseEntity<ApiResponse<List<Order>>> getOrdersByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(orderService.getOrdersByUserId(id)));
    }
}

//git commit -m "feat: 加入 orders 功能、統一回傳格式、狀態碼"