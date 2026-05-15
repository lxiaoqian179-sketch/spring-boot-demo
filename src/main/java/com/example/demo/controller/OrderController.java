package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
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

//    @GetMapping("/orders")
//    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
//        return ResponseEntity.ok(ApiResponse.success(orderService.getAllOrders()));
//    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getOrders(
            @RequestParam(required = false) String status) {
        if (status != null) {
            List<Order> orders = orderService.findByStatus(status);
            return ResponseEntity.ok(ApiResponse.success(orders));
        }
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(ApiResponse.success(orders));
    }

    @GetMapping("/users/{id}/orders")
    public ResponseEntity<ApiResponse<List<Order>>> getOrdersByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(orderService.getOrdersByUserId(id)));
    }
}

//git commit -m "feat: 加入 orders 功能、統一回傳格式、狀態碼"