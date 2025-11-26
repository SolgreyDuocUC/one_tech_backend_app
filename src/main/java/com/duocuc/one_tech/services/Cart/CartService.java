package com.duocuc.one_tech.services.Cart;
import com.duocuc.one_tech.dto.cart.CartDTO;
import exceptions.NotFoundException; // <-- Importar la excepción

public interface CartService {

    CartDTO getCartById(Long cartId) throws NotFoundException;

    CartDTO createCartForUser(Long userId) throws NotFoundException;

    CartDTO addItem(Long cartId, Long productId, Integer qty) throws NotFoundException;

    CartDTO updateItemQty(Long cartId, Long itemId, Integer qty) throws NotFoundException;

    CartDTO removeItem(Long cartId, Long itemId) throws NotFoundException;

    CartDTO clearCart(Long cartId) throws NotFoundException;

    CartDTO getCartByUserId(Long userId);
}
