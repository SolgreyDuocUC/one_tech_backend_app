package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.cart.CartDTO;
import com.duocuc.one_tech.dto.cart.dto.UpdateCartItemQtyRequest;
import com.duocuc.one_tech.services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duocuc.one_tech.services.CartService;
import com.duocuc.one_tech.dto.cart.dto.AddCartItemRequest;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;
    private final DiscountService discountService;

    public CartController(CartService cartService,
                          DiscountService discountService) {
        this.cartService = cartService;
        this.discountService = discountService;
    }

    // GET /api/carts/{cartId}
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCartById(cartId));
    }

    // POST /api/carts?userId=1
    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestParam Long userId) {
        CartDTO dto = cartService.createCartForUser(userId);
        return ResponseEntity.ok(dto);
    }

    // POST /api/carts/{cartId}/items
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDTO> addItem(@PathVariable Long cartId,
                                           @RequestBody AddCartItemRequest request) {
        CartDTO dto = cartService.addItem(cartId, request.productId(), request.qty());
        return ResponseEntity.ok(dto);
    }

    // PUT /api/carts/{cartId}/items/{itemId}
    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDTO> updateItemQty(@PathVariable Long cartId,
                                                 @PathVariable Long itemId,
                                                 @RequestBody UpdateCartItemQtyRequest request) {
        CartDTO dto = cartService.updateItemQty(cartId, itemId, request.qty());
        return ResponseEntity.ok(dto);
    }

    // DELETE /api/carts/{cartId}/items/{itemId}
    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDTO> removeItem(@PathVariable Long cartId,
                                              @PathVariable Long itemId) {
        CartDTO dto = cartService.removeItem(cartId, itemId);
        return ResponseEntity.ok(dto);
    }

    // DELETE /api/carts/{cartId}/items  -> vaciar carrito
    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<CartDTO> clearCart(@PathVariable Long cartId) {
        CartDTO dto = cartService.clearCart(cartId);
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/{cartId}/apply-discount")
    public ResponseEntity<CartDTO> applyDiscount(@PathVariable Long cartId,
                                                 @RequestParam String code) {
        CartDTO dto = discountService.applyDiscountToCart(cartId, code);
        return ResponseEntity.ok(dto);
    }

}
