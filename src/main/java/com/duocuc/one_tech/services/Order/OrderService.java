package com.duocuc.one_tech.services.Order;

import com.duocuc.one_tech.dto.order.OrderDTO;
import com.duocuc.one_tech.dto.order.dto.OrderStatusUpdateDTO;
import com.duocuc.one_tech.dto.order.dto.OrderUpdateDTO;
import exceptions.NotFoundException;

import java.util.List;

public interface OrderService {

    OrderDTO createOrderFromCart(Long cartId) throws NotFoundException;

    OrderDTO getOrderById(Long orderId) throws NotFoundException;

    List<OrderDTO> getOrdersByUser(Long userId) throws NotFoundException;

    List<OrderDTO> getAllOrders();

    OrderDTO actualizarEstado(Long orderId, OrderStatusUpdateDTO dto);

    OrderDTO actualizarOrden(Long orderId, OrderUpdateDTO dto);

    void eliminarOrden(Long orderId);
}
