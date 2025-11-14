package com.duocuc.one_tech.services;
import com.duocuc.one_tech.dto.cart.CartDTO;

public interface CartService {

    CartDTO getCartById(Long cartId);

    CartDTO createCartForUser(Long userId);

    CartDTO addItem(Long cartId, Long productId, Integer qty);

    CartDTO updateItemQty(Long cartId, Long itemId, Integer qty);

    CartDTO removeItem(Long cartId, Long itemId);

    CartDTO clearCart(Long cartId);
}
