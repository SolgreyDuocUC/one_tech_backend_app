package com.duocuc.one_tech.services;

import com.duocuc.one_tech.dto.order.OrderDTO;
import com.duocuc.one_tech.dto.order.OrderMapper;
import com.duocuc.one_tech.models.*;
import com.duocuc.one_tech.repositories.CartRepository;
import com.duocuc.one_tech.repositories.OrderRepository;
import com.duocuc.one_tech.repositories.UserRepository;

import exceptions.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CartRepository cartRepository,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderDTO createOrderFromCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Carrito no encontrado con id " + cartId));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío, no se puede crear una orden");
        }

        User user = cart.getUser();
        if (user == null || user.getId() == null) {
            throw new IllegalStateException("El carrito no tiene usuario asociado");
        }

        // Calcular totales a partir del carrito
        BigDecimal itemsTotal = cart.getItems().stream()
                .map(ci -> ci.getUnitPrice().multiply(BigDecimal.valueOf(ci.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discountTotal = cart.getDiscountTotal() != null
                ? cart.getDiscountTotal()
                : BigDecimal.ZERO;

        BigDecimal grandTotal = itemsTotal.subtract(discountTotal);

        Order order = new Order();
        order.setUser(user);
        order.setItems(new ArrayList<>());
        order.setItemsTotal(itemsTotal);
        order.setDiscountTotal(discountTotal);
        order.setGrandTotal(grandTotal);


        try {
            order.setStatus("CREATED");
        } catch (Exception ignored) {

        }

        try {
            order.setCreatedAt(OffsetDateTime.now());
        } catch (Exception ignored) {
        }

        // Crear OrderItems a partir de CartItems
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);

            oi.setNameSnapshot(ci.getProduct().getName());

            oi.setUnitPrice(ci.getUnitPrice());
            oi.setQty(ci.getQty());


            oi.computeTotal();

            order.getItems().add(oi);
        }

        Order saved = orderRepository.save(order);

        // Opcional: vaciar carrito después de crear la orden
        cart.getItems().clear();
        cart.setItemsTotal(BigDecimal.ZERO);
        cart.setDiscountTotal(BigDecimal.ZERO);
        cart.setGrandTotal(BigDecimal.ZERO);
        cartRepository.save(cart);

        return OrderMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Orden no encontrada con id " + orderId));
        return OrderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + userId));

        return orderRepository.findByUser(user)
                .stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDto)
                .toList();
    }
}
