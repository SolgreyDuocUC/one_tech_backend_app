package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.review.ReviewDTO;
import com.duocuc.one_tech.dto.review.dto.ReviewRequest;
import com.duocuc.one_tech.services.Review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // GET /api/reviews/product/{productId}
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }

    // POST /api/reviews/product/{productId}?userId=1
    @PostMapping("/product/{productId}")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable Long productId,
                                                  @RequestParam Long userId,
                                                  @RequestBody ReviewRequest request) {
        ReviewDTO dto = reviewService.createReview(productId, userId, request);
        return ResponseEntity.ok(dto);
    }

    // PUT /api/reviews/{reviewId}
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long reviewId,
                                                  @RequestBody ReviewRequest request) {
        ReviewDTO dto = reviewService.updateReview(reviewId, request);
        return ResponseEntity.ok(dto);
    }

    // DELETE /api/reviews/{reviewId}
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
