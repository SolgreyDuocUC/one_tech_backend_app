package com.duocuc.one_tech.services;

import com.duocuc.one_tech.dto.cart.CartDTO;
import com.duocuc.one_tech.dto.discount.DiscountCreateUpdateRequest;
import com.duocuc.one_tech.dto.discount.DiscountDTO;

import java.util.List;

public interface DiscountService {


    CartDTO applyDiscountToCart(Long cartId, String code);

    // CRUD
    List<DiscountDTO> getAllDiscounts();

    DiscountDTO getDiscountById(Long id);

    DiscountDTO createDiscount(DiscountCreateUpdateRequest request);

    DiscountDTO updateDiscount(Long id, DiscountCreateUpdateRequest request);

    void deleteDiscount(Long id); // si quieres, puedes hacer soft delete en vez de borrar
}
