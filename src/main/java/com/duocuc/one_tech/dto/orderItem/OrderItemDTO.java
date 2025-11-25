package com.duocuc.one_tech.dto.orderItem;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemDTO {
    private Long id;
    private String nameSnapshot;
    private BigDecimal unitPrice;
    private Integer qty;
    private BigDecimal total;
    private Long orderId;
}
