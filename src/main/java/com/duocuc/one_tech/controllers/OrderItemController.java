package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.orderItem.CreateOrderItemDTO;
import com.duocuc.one_tech.dto.orderItem.OrderItemDTO;
import com.duocuc.one_tech.dto.orderItem.UpdateOrderItemDTO;
import com.duocuc.one_tech.services.OrderItem.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody CreateOrderItemDTO dto) {
        return ResponseEntity.ok(orderItemService.createOrderItem(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItem(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long id, @RequestBody UpdateOrderItemDTO dto) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}

