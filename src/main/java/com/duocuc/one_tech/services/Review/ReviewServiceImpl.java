package com.duocuc.one_tech.services.Review;

import com.duocuc.one_tech.dto.review.ReviewDTO;
import com.duocuc.one_tech.dto.review.ReviewMapper;
import com.duocuc.one_tech.dto.review.dto.ReviewRequest;
import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.models.Review;
import com.duocuc.one_tech.models.User;
import com.duocuc.one_tech.repositories.ProductRepository;
import com.duocuc.one_tech.repositories.ReviewRepository;
import com.duocuc.one_tech.repositories.UserRepository;
import com.duocuc.one_tech.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ProductRepository productRepository,
                             UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviewsByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + productId));

        return reviewRepository.findByProduct(product)
                .stream()
                .map(ReviewMapper::toDto)
                .toList();
    }

    @Override
    public ReviewDTO createReview(Long productId, Long userId, ReviewRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + userId));

        Review review = new Review();
        review.setProduct(product);
        review.setAuthor(user);
        review.setRating(request.rating());
        review.setContent(request.comment());

        try {
            review.setCreatedAt(OffsetDateTime.now());  // si tu entidad tiene este campo
        } catch (Exception ignored) {}

        Review saved = reviewRepository.save(review);
        return ReviewMapper.toDto(saved);
    }

    @Override
    public ReviewDTO updateReview(Long reviewId, ReviewRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review no encontrada con id " + reviewId));

        review.setRating(request.rating());
        review.setContent(request.comment());

        Review saved = reviewRepository.save(review);
        return ReviewMapper.toDto(saved);
    }

    @Override
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new NotFoundException("Review no encontrada con id " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}
