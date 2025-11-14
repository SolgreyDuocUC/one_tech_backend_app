package com.duocuc.one_tech.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS" )
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Column(name = "order_number", length = 20, nullable = false, unique = true)
    @NotBlank
    private String orderNumber;

    @Column(name = "status_order", length = 20, nullable = false)
    private String status;

    @Column(name = "items_total_order", precision = 12, scale = 2, nullable = false)
    private  BigDecimal itemsTotal = BigDecimal.ZERO;

    @Column(name = "shipping_total_order", precision = 12, scale = 2, nullable = false)
    private  BigDecimal shippingTotal = BigDecimal.ZERO;

    @Column(name = "discount_total_order", precision = 12, scale = 2, nullable = false)
    private  BigDecimal discountTotal = BigDecimal.ZERO;

    @Column(name = "grand_total_order", precision = 12, scale = 2, nullable = false)
    private  BigDecimal grandTotal = BigDecimal.ZERO;

    @Column(name = "payment_method_order", length = 20, nullable = false)
    private String paymentMethod;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @Column(name = "paid_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime paidAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items= new ArrayList<>();

    public void addItem(OrderItem item){
        item.setOrder(this);
        items.add(item);
    }
    public void removeItem(OrderItem item){
        items.remove(item);
        item.setOrder(null);
    }
    public void recalcTotals(){
        this.itemsTotal = items.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.grandTotal = itemsTotal
                .add(shippingTotal != null ? shippingTotal : BigDecimal.ZERO)
                .subtract(discountTotal != null ? discountTotal : BigDecimal.ZERO);
    }


}
