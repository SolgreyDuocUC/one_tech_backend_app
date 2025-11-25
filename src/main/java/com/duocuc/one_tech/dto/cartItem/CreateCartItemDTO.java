package com.duocuc.one_tech.dto.cartItem;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateCartItemDTO {
    private Integer qty;
    private BigDecimal unitPrice;
    private Long cartId;
    private Long productId;
}
