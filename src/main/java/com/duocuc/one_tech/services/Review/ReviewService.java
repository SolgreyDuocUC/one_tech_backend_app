package com.duocuc.one_tech.services.Review;

import com.duocuc.one_tech.dto.review.ReviewDTO;
import com.duocuc.one_tech.dto.review.dto.ReviewRequest;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getReviewsByProduct(Long productId);

    ReviewDTO createReview(Long productId, Long userId, ReviewRequest request);

    ReviewDTO updateReview(Long reviewId, ReviewRequest request);

    void deleteReview(Long reviewId);
}
