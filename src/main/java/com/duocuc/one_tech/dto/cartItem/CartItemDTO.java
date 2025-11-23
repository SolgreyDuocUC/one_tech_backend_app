package com.duocuc.one_tech.dto.cartItem;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItemDTO {
    private Long id;
    private Integer qty;
    private BigDecimal unitPrice;
    private BigDecimal total;
    private Long cartId;
    private Long productId;
}
