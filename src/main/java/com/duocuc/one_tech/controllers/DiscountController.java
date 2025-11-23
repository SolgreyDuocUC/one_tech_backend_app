package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.cart.CartDTO;
import com.duocuc.one_tech.dto.discount.DiscountCreateUpdateRequest;
import com.duocuc.one_tech.dto.discount.DiscountDTO;
import com.duocuc.one_tech.services.Discount.DiscountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    // Aplicar descuento a un carrito
    @PostMapping("/apply")
    public ResponseEntity<CartDTO> applyDiscount(
            @RequestParam Long cartId,
            @RequestParam String code) {
        CartDTO updatedCart = discountService.applyDiscountToCart(cartId, code);
        return ResponseEntity.ok(updatedCart);
    }

    // Obtener todos los descuentos
    @GetMapping
    public ResponseEntity<List<DiscountDTO>> getAllDiscounts() {
        List<DiscountDTO> discounts = discountService.getAllDiscounts();
        return ResponseEntity.ok(discounts);
    }

    // Obtener un descuento por ID
    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> getDiscountById(@PathVariable Long id) {
        DiscountDTO discount = discountService.getDiscountById(id);
        return ResponseEntity.ok(discount);
    }

    // Crear un nuevo descuento
    @PostMapping
    public ResponseEntity<DiscountDTO> createDiscount(
            @Valid @RequestBody DiscountCreateUpdateRequest request) {
        DiscountDTO created = discountService.createDiscount(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Actualizar un descuento
    @PutMapping("/{id}")
    public ResponseEntity<DiscountDTO> updateDiscount(
            @PathVariable Long id,
            @Valid @RequestBody DiscountCreateUpdateRequest request) {
        DiscountDTO updated = discountService.updateDiscount(id, request);
        return ResponseEntity.ok(updated);
    }

    // Eliminar un descuento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }
}

