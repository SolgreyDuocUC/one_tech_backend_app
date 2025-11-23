package com.duocuc.one_tech.dto.orderItem;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateOrderItemDTO {
    private String nameSnapshot;
    private BigDecimal unitPrice;
    private Integer qty;
    private Long orderId;
}
