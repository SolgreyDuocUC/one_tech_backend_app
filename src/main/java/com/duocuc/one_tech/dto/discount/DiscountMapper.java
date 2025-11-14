package com.duocuc.one_tech.dto.discount;

import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.models.Discount;

public class DiscountMapper {

    public static DiscountDTO toDto(Discount d) {
        if (d == null) return null;

        Long categoryId = null;
        Category cat = d.getAppliesToCategory();
        if (cat != null) categoryId = cat.getId();

        return new DiscountDTO(
                d.getId(),
                d.getCode(),
                d.getDescription(),
                d.getDiscountType(),
                d.getValue(),          // tu campo se llama "Value", getter es getValue()
                d.getMinPurchase(),
                d.getStartDate(),
                d.getEndDate(),
                d.getIsActive(),
                categoryId
        );
    }

    public static void updateEntityFromRequest(Discount d,
                                               DiscountCreateUpdateRequest req,
                                               Category category) {

        d.setCode(req.code());
        d.setDescription(req.description());
        d.setDiscountType(req.discountType());
        d.setValue(req.value());
        d.setMinPurchase(req.minPurchase());
        d.setStartDate(req.startDate());
        d.setEndDate(req.endDate());
        d.setIsActive(req.isActive());
        d.setAppliesToCategory(category);
    }
}
