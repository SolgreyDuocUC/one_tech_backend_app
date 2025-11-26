package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.cartItem.CartItemDTO;
import com.duocuc.one_tech.dto.cartItem.CreateCartItemDTO;
import com.duocuc.one_tech.dto.cartItem.UpdateCartItemDTO;
import com.duocuc.one_tech.services.CartItem.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItemDTO> createCartItem(@RequestBody CreateCartItemDTO dto) {
        return ResponseEntity.ok(cartItemService.createCartItem(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> getCartItem(@PathVariable Long id) {
        return ResponseEntity.ok(cartItemService.getCartItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long id, @RequestBody UpdateCartItemDTO dto) {
        return ResponseEntity.ok(cartItemService.updateCartItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
