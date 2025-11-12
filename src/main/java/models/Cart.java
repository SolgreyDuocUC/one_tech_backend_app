package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CARTS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private Long id;

    @Column(name = "status_cart", length = 20, nullable = false)
    private String status;

    @Column(name = "items_total_cart", precision = 12, scale = 2, nullable = false)
    private BigDecimal itemsTotal = BigDecimal.ZERO;

    @Column(name = "discount_total_cart", precision = 12, scale = 2, nullable = false)
    private   BigDecimal discountTotal = BigDecimal.ZERO;

    @Column(name = "grand_total_cart", precision = 12, scale = 2, nullable = false)
    private   BigDecimal grandTotal = BigDecimal.ZERO;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem it) {it.setCart(this); items.add(it);}
    public void recalcTotals(){
        this.itemsTotal = items.stream().map(CartItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.grandTotal = itemsTotal.subtract(discountTotal != null ? discountTotal : BigDecimal.ZERO);
    }
}
