package com.duocuc.one_tech.services.Discount;

import com.duocuc.one_tech.dto.cart.CartDTO;
import com.duocuc.one_tech.dto.cart.CartMapper;
import com.duocuc.one_tech.dto.discount.DiscountCreateUpdateRequest;
import com.duocuc.one_tech.dto.discount.DiscountDTO;
import com.duocuc.one_tech.dto.discount.DiscountMapper;
import com.duocuc.one_tech.exceptions.NotFoundException;
import com.duocuc.one_tech.models.Cart;
import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.models.Discount;
import com.duocuc.one_tech.repositories.CartRepository;
import com.duocuc.one_tech.repositories.CategoryRepository;
import com.duocuc.one_tech.repositories.DiscountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

    private final CartRepository cartRepository;
    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;

    public DiscountServiceImpl(CartRepository cartRepository,
                               DiscountRepository discountRepository,
                               CategoryRepository categoryRepository) {
        this.cartRepository = cartRepository;
        this.discountRepository = discountRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CartDTO applyDiscountToCart(Long cartId, String code) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Carrito no encontrado con id " + cartId));

        Discount discount = discountRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Código de descuento inválido: " + code));

        if (!Boolean.TRUE.equals(discount.getIsActive())) {
            throw new IllegalStateException("El código no está activo");
        }

        OffsetDateTime now = OffsetDateTime.now();

        if (discount.getStartDate() != null && now.isBefore(discount.getStartDate())) {
            throw new IllegalStateException("El descuento aún no está vigente");
        }

        if (discount.getEndDate() != null && now.isAfter(discount.getEndDate())) {
            throw new IllegalStateException("El descuento ha expirado");
        }

        BigDecimal subtotal = cart.getItemsTotal();
        if (discount.getMinPurchase() != null &&
                subtotal.compareTo(discount.getMinPurchase()) < 0) {
            throw new IllegalStateException("El subtotal no alcanza el mínimo para aplicar el descuento");
        }

        BigDecimal discountAmount = BigDecimal.ZERO;

        if ("percentage".equalsIgnoreCase(discount.getDiscountType())) {
            BigDecimal pct = discount.getValue()
                    .divide(BigDecimal.valueOf(100));
            discountAmount = subtotal.multiply(pct);

        } else if ("fixed".equalsIgnoreCase(discount.getDiscountType())) {
            discountAmount = discount.getValue();
        } else {
            throw new IllegalStateException("Tipo de descuento no reconocido");
        }

        if (discountAmount.compareTo(subtotal) > 0) {
            discountAmount = subtotal;
        }

        cart.setDiscountTotal(discountAmount);
        cart.setGrandTotal(subtotal.subtract(discountAmount));

        Cart saved = cartRepository.save(cart);
        return CartMapper.toDto(saved);
    }


    //   CRUD


    @Override
    @Transactional(readOnly = true)
    public List<DiscountDTO> getAllDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map(DiscountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DiscountDTO getDiscountById(Long id) {
        Discount d = discountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Descuento no encontrado con id " + id));
        return DiscountMapper.toDto(d);
    }

    @Override
    public DiscountDTO createDiscount(DiscountCreateUpdateRequest request) {
        Discount d = new Discount();

        Category category = null;
        if (request.appliesToCategoryId() != null) {
            category = categoryRepository.findById(request.appliesToCategoryId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada con id " + request.appliesToCategoryId()));
        }

        DiscountMapper.updateEntityFromRequest(d, request, category);

        Discount saved = discountRepository.save(d);
        return DiscountMapper.toDto(saved);
    }

    @Override
    public DiscountDTO updateDiscount(Long id, DiscountCreateUpdateRequest request) {
        Discount d = discountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Descuento no encontrado con id " + id));

        Category category = null;
        if (request.appliesToCategoryId() != null) {
            category = categoryRepository.findById(request.appliesToCategoryId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada con id " + request.appliesToCategoryId()));
        }

        DiscountMapper.updateEntityFromRequest(d, request, category);

        Discount saved = discountRepository.save(d);
        return DiscountMapper.toDto(saved);
    }

    @Override
    public void deleteDiscount(Long id) {
        if (!discountRepository.existsById(id)) {
            throw new NotFoundException("Descuento no encontrado con id " + id);
        }
        discountRepository.deleteById(id);

    }
}
