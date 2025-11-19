package com.duocuc.one_tech.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
    @NotBlank(message = "El número de orden no puede estar vacío.")
    @Size(min = 1, max = 20, message = "El número de orden debe tener entre 1 y 20 caracteres.")
    private String orderNumber;

    @Column(name = "status_order", length = 20, nullable = false)
    @NotNull(message = "El estado de la orden no puede ser nulo.")
    private String status;

    @Column(name = "items_total_order", precision = 12, scale = 2, nullable = false)
    @NotNull
    private  BigDecimal itemsTotal = BigDecimal.ZERO;

    @Column(name = "shipping_total_order", precision = 12, scale = 2, nullable = false)
    @NotNull
    private  BigDecimal shippingTotal = BigDecimal.ZERO;

    @Column(name = "discount_total_order", precision = 12, scale = 2, nullable = false)
    @NotNull
    private  BigDecimal discountTotal = BigDecimal.ZERO;

    @Column(name = "grand_total_order", precision = 12, scale = 2, nullable = false)
    @NotNull
    private  BigDecimal grandTotal = BigDecimal.ZERO;

    @Column(name = "payment_method_order", length = 20, nullable = false)
    @NotNull(message = "El método de pago no puede ser nulo.")
    private String paymentMethod;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @Column(name = "paid_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime paidAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users", nullable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

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
                .filter(java.util.Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal ship = shippingTotal != null ? shippingTotal : BigDecimal.ZERO;
        BigDecimal discount = discountTotal != null ? discountTotal : BigDecimal.ZERO;

        this.grandTotal = itemsTotal.add(ship).subtract(discount);
    }
}