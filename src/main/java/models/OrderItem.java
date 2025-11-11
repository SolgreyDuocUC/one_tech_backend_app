package models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Table(name = "ORDER_ITEMS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_item")
    private Long id;

    @Column(name = "name_snapshot_order_item", length = 120, nullable = false)
    @NotBlank
    private String nameSnapshot;

    @Column(name = "unit_price_order_item", precision = 12, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "qty_order_item", nullable = false)
    @Min(1)
    private Integer qty;

    @Column(name = "total_order_item", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @PrePersist @PreUpdate
    public void computeTotal() {
        if (unitPrice != null && qty != null) {
            this.total = unitPrice.multiply(BigDecimal.valueOf(qty));

        }
    }
}
