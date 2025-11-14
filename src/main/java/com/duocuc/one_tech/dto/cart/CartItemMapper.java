package com.duocuc.one_tech.dto.cart;

import com.duocuc.one_tech.models.CartItem;
import com.duocuc.one_tech.models.Product;

public class CartItemMapper {

    public static CartItemDTO toDto(CartItem item) {
        if (item == null) return null;

        Product p = item.getProduct();

        return new CartItemDTO(
                item.getId(),
                p != null ? p.getId() : null,
                p != null ? p.getName() : null,
                item.getQty(),
                item.getUnitPrice(),
                item.getTotal()
        );
    }
}
