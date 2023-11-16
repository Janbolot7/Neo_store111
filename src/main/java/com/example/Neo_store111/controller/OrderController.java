package com.example.Neo_store111.controller;

import com.example.Neo_store111.dto.OrderDto;
import com.example.Neo_store111.dto.OrderInfo;
import com.example.Neo_store111.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vv/orders")
@AllArgsConstructor
@Transactional
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/allOrders")
    public List<OrderInfo> allOrders() {
        return orderService.allOrders();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
}