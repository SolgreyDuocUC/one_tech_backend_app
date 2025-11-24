package com.duocuc.one_tech.config;

import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.models.Review;
import com.duocuc.one_tech.repositories.ProductRepository;
import com.duocuc.one_tech.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initReviews(
            ProductRepository productRepository,
            ReviewRepository reviewRepository
    ) {
        return args -> {

            // Evitar duplicados
            if (reviewRepository.count() > 0) {
                System.out.println("Reviews ya existentes, no se insertan datos.");
                return;
            }

            System.out.println("Insertando reviews de ejemplo...");

            //  Producto ya existente en tu BD
            Product product = productRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Producto con ID 1 no existe"));

            Review review1 = new Review();
            review1.setProduct(product);
            review1.setRating(5);
            review1.setTitle("Excelente producto");
            review1.setContent("Muy buen mouse, lo recomiendo totalmente.");

            Review review2 = new Review();
            review2.setProduct(product);
            review2.setRating(4);
            review2.setTitle("Muy bueno");
            review2.setContent("Me gustó bastante, aunque esperaba más.");

            reviewRepository.save(review1);
            reviewRepository.save(review2);

            System.out.println("Reviews insertadas correctamente ");
        };
    }
}
