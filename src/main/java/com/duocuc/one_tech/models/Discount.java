package com.duocuc.one_tech.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "DISCOUNTS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount")
    private Long id;

    @Column(name = "code", length = 30, nullable = false, unique = true)
    private String code;

    @Column(name = "description", length = 120)
    private String description;

    @Column(name = "discount_type", length = 10, nullable = false)
    private String discountType;

    @Column(name = "discount_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal Value;

    @Column(name = "min_purchase", precision = 12, scale = 2)
    private BigDecimal minPurchase;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime startDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime endDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applies_to_category")
    private Category appliesToCategory;
}
