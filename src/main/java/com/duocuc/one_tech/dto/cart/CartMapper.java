package com.duocuc.one_tech.dto.cart;

import com.duocuc.one_tech.models.Cart;
import com.duocuc.one_tech.models.User;

import java.util.List;

public class CartMapper {

    public static CartDTO toDto(Cart cart) {
        if (cart == null) return null;

        User u = cart.getUser();
        Long userId = (u != null ? u.getId() : null);

        List<CartItemDTO> itemsDto = cart.getItems()
                .stream()
                .map(CartItemMapper::toDto)
                .toList();

        return new CartDTO(
                cart.getId(),
                userId,
                cart.getItemsTotal(),
                cart.getDiscountTotal(),
                cart.getGrandTotal(),
                itemsDto
        );
    }
}
