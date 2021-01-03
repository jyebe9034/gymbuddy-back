package com.gymbuddy.backgymbuddy.admin.review.repository;

import com.gymbuddy.backgymbuddy.admin.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
