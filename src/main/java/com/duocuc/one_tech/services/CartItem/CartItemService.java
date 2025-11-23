package com.duocuc.one_tech.services.CartItem;

import com.duocuc.one_tech.dto.cartItem.CartItemDTO;
import com.duocuc.one_tech.dto.cartItem.CreateCartItemDTO;
import com.duocuc.one_tech.dto.cartItem.UpdateCartItemDTO;

import java.util.List;

public interface CartItemService {
    CartItemDTO createCartItem(CreateCartItemDTO dto);
    CartItemDTO getCartItemById(Long id);
    List<CartItemDTO> getAllCartItems();
    CartItemDTO updateCartItem(Long id, UpdateCartItemDTO dto);
    void deleteCartItem(Long id);
}
