package com.example.sentiment.service;

import com.example.sentiment.entiy.Review;
import com.example.sentiment.entiy.Sentiment;
import com.example.sentiment.model.ReviewRepository;
import com.example.sentiment.model.SentimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SentimentRepository sentimentRepository;

    @Autowired
    private SentimentService sentimentService;

    public Review submitReview(String firstName, String lastName, String location, String comment) {
        Review review = new Review(firstName, lastName, location, comment, LocalDateTime.now());
        review = reviewRepository.save(review);

        // Δημιουργία - Ανάλυση συναισθήματος
        Sentiment sentiment = sentimentService.analyzeSentiment(comment,review);

        // Αποθήκευση του Sentiment
        sentimentRepository.save(sentiment);

        review.setSentiment(sentiment);
        return reviewRepository.save(review);
    }
}
