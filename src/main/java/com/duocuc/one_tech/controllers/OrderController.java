package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.order.OrderDTO;
import com.duocuc.one_tech.services.Order.OrderService;
import exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/orders/from-cart/{cartId}
    @PostMapping("/from-cart/{cartId}")
    public ResponseEntity<OrderDTO> createFromCart(@PathVariable Long cartId) throws NotFoundException {
        OrderDTO dto = orderService.createOrderFromCart(cartId);
        return ResponseEntity.ok(dto);
    }

    // GET /api/orders/{orderId}
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) throws NotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    // GET /api/orders/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId) throws NotFoundException {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    // GET /api/orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}

