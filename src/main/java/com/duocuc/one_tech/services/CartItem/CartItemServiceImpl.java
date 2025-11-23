package com.duocuc.one_tech.services.CartItem;

import com.duocuc.one_tech.dto.cartItem.CartItemDTO;
import com.duocuc.one_tech.dto.cartItem.CreateCartItemDTO;
import com.duocuc.one_tech.dto.cartItem.UpdateCartItemDTO;
import com.duocuc.one_tech.exceptions.ResourceNotFoundException;
import com.duocuc.one_tech.models.Cart;
import com.duocuc.one_tech.models.CartItem;
import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.repositories.CartItemRepository;
import com.duocuc.one_tech.repositories.CartRepository;
import com.duocuc.one_tech.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartItemDTO createCartItem(CreateCartItemDTO dto) {
        Cart cart = cartRepository.findById(dto.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + dto.getCartId()));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + dto.getProductId()));

        CartItem item = CartItem.builder()
                .qty(dto.getQty())
                .unitPrice(dto.getUnitPrice())
                .cart(cart)
                .product(product)
                .build();

        CartItem saved = cartItemRepository.save(item);
        return mapToDTO(saved);
    }

    @Override
    public CartItemDTO getCartItemById(Long id) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id " + id));
        assert item != null;
        return mapToDTO(item);
    }

    @Override
    public List<CartItemDTO> getAllCartItems() {
        return cartItemRepository.findAll().stream().filter(Objects::nonNull)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemDTO updateCartItem(Long id, UpdateCartItemDTO dto) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id " + id));

        assert item != null;
        item.setQty(dto.getQty());
        item.setUnitPrice(dto.getUnitPrice());

        CartItem updated = cartItemRepository.save(item);
        return mapToDTO(updated);
    }

    @Override
    public void deleteCartItem(Long id) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id " + id));
        assert item != null;
        cartItemRepository.delete(item);
    }

    private CartItemDTO mapToDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setQty(item.getQty());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setTotal(item.getTotal());
        dto.setCartId(item.getCart().getId());
        dto.setProductId(item.getProduct().getId());
        return dto;
    }
}

