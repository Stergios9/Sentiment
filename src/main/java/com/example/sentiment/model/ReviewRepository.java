package com.example.sentiment.model;

import com.example.sentiment.entiy.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
