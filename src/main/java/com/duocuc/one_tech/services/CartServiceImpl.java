package com.duocuc.one_tech.services;

import com.duocuc.one_tech.dto.cart.CartDTO;
import com.duocuc.one_tech.dto.cart.CartMapper;
import exceptions.NotFoundException;
import com.duocuc.one_tech.models.Cart;
import com.duocuc.one_tech.models.CartItem;
import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.duocuc.one_tech.repositories.CartItemRepository;
import com.duocuc.one_tech.repositories.CartRepository;
import com.duocuc.one_tech.repositories.ProductRepository;
import com.duocuc.one_tech.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CartDTO getCartById(Long cartId) {
        Cart cart = findCartOrThrow(cartId);
        return CartMapper.toDto(cart);
    }

    @Override
    public CartDTO createCartForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + userId));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(new ArrayList<>());
        cart.setItemsTotal(BigDecimal.ZERO);
        cart.setDiscountTotal(BigDecimal.ZERO);
        cart.setGrandTotal(BigDecimal.ZERO);

        Cart saved = cartRepository.save(cart);
        return CartMapper.toDto(saved);
    }

    @Override
    public CartDTO addItem(Long cartId, Long productId, Integer qty) {
        if (qty == null || qty <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }

        Cart cart = findCartOrThrow(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + productId));

        // ¿Ya existe un item de ese producto en el carrito?
        CartItem existing = cart.getItems().stream()
                .filter(it -> it.getProduct() != null && it.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setQty(existing.getQty() + qty);
            existing.computeTotal();
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQty(qty);
            item.setUnitPrice(product.getPrice());
            item.computeTotal();

            cart.getItems().add(item);
        }

        cart.recalcTotals();
        Cart saved = cartRepository.save(cart);
        return CartMapper.toDto(saved);
    }

    @Override
    public CartDTO updateItemQty(Long cartId, Long itemId, Integer qty) {
        if (qty == null || qty <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }

        Cart cart = findCartOrThrow(cartId);

        CartItem item = cart.getItems().stream()
                .filter(it -> it.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Item no encontrado en este carrito con id " + itemId));

        item.setQty(qty);
        item.computeTotal();

        cart.recalcTotals();
        Cart saved = cartRepository.save(cart);
        return CartMapper.toDto(saved);
    }

    @Override
    public CartDTO removeItem(Long cartId, Long itemId) {
        Cart cart = findCartOrThrow(cartId);

        List<CartItem> items = cart.getItems();
        boolean removed = items.removeIf(it -> it.getId().equals(itemId));

        if (!removed) {
            throw new NotFoundException("Item no encontrado en este carrito con id " + itemId);
        }

        cart.recalcTotals();
        Cart saved = cartRepository.save(cart);
        return CartMapper.toDto(saved);
    }

    @Override
    public CartDTO clearCart(Long cartId) {
        Cart cart = findCartOrThrow(cartId);

        cart.getItems().clear();
        cart.setItemsTotal(BigDecimal.ZERO);
        cart.setDiscountTotal(BigDecimal.ZERO);
        cart.setGrandTotal(BigDecimal.ZERO);

        Cart saved = cartRepository.save(cart);
        return CartMapper.toDto(saved);
    }

    private Cart findCartOrThrow(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Carrito no encontrado con id " + cartId));
    }
}
