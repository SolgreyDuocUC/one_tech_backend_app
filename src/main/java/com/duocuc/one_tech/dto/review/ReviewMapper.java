package com.duocuc.one_tech.dto.review;

import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.models.Review;
import com.duocuc.one_tech.models.User;

public class ReviewMapper {

    public static ReviewDTO toDto(Review review) {
        if (review == null) return null;

        Product p = review.getProduct();   // ajusta si tu getter se llama distinto
        User u = review.getAuthor();         // idem

        Long productId = p != null ? p.getId() : null;
        Long userId = u != null ? u.getId() : null;

        // Ajusta el getter de nombre de usuario según tu modelo:
        //   getFullName(), getFullnameUsers(), getNameUsers(), etc.
        String userName = u != null ? u.getLastName() : null;

        return new ReviewDTO(
                review.getId(),
                productId,
                userId,
                userName,
                review.getRating(),        // ajusta getters según tu entidad
                review.getContent(),
                review.getCreatedAt()      // o null si no tienes fecha
        );
    }
}
