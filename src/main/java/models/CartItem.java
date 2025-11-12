package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CART_ITEMS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart_item")
    private Long id;

    @Column(name = "qty_cart_item", nullable = false)
    private Integer qty;

    @Column(name = "unit_price_cart_item", precision = 12, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "total_cart_item", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cart", nullable = false)
    private Cart cart;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @PrePersist @PreUpdate
    public void computeTotal() {
        if (unitPrice != null && qty != null) this.total = unitPrice.multiply(BigDecimal.valueOf(qty));
    }
}
