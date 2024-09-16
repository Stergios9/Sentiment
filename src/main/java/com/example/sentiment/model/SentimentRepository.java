package com.example.sentiment.model;

import com.example.sentiment.entiy.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {
    Optional<Sentiment> findByReviewId(Long reviewId);
}
