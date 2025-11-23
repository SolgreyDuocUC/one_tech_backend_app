package com.duocuc.one_tech.services.OrderItem;

import com.duocuc.one_tech.dto.orderItem.CreateOrderItemDTO;
import com.duocuc.one_tech.dto.orderItem.OrderItemDTO;
import com.duocuc.one_tech.dto.orderItem.UpdateOrderItemDTO;
import com.duocuc.one_tech.exceptions.ResourceNotFoundException;
import com.duocuc.one_tech.models.Order;
import com.duocuc.one_tech.models.OrderItem;
import com.duocuc.one_tech.repositories.OrderItemRepository;
import com.duocuc.one_tech.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderItemDTO createOrderItem(CreateOrderItemDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + dto.getOrderId()));

        OrderItem item = OrderItem.builder()
                .nameSnapshot(dto.getNameSnapshot())
                .unitPrice(dto.getUnitPrice())
                .qty(dto.getQty())
                .order(order)
                .build();

        OrderItem saved = orderItemRepository.save(item);
        return mapToDTO(saved);
    }

    @Override
    public OrderItemDTO getOrderItemById(Long id) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id " + id));
        return mapToDTO(item);
    }

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDTO updateOrderItem(Long id, UpdateOrderItemDTO dto) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id " + id));

        item.setQty(dto.getQty());
        item.setUnitPrice(dto.getUnitPrice());

        OrderItem updated = orderItemRepository.save(item);
        return mapToDTO(updated);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id " + id));
        orderItemRepository.delete(item);
    }

    private OrderItemDTO mapToDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setNameSnapshot(item.getNameSnapshot());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setQty(item.getQty());
        dto.setTotal(item.getTotal());
        dto.setOrderId(item.getOrder().getId());
        return dto;
    }
}
