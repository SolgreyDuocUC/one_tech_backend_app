package com.duocuc.one_tech.dto.orderItem;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateOrderItemDTO {
    private BigDecimal unitPrice;
    private Integer qty;
}
