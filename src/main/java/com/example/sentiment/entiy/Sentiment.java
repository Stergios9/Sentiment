package com.example.sentiment.entiy;

import jakarta.persistence.*;

@Entity
public class Sentiment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String predictedSentiment;

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;

    // Default constructor
    public Sentiment() {}

    // Parameterized constructor
    public Sentiment(String predictedSentiment, Review review) {
        this.predictedSentiment = predictedSentiment;
        this.review = review;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPredictedSentiment() {
        return predictedSentiment;
    }

    public void setPredictedSentiment(String predictedSentiment) {
        this.predictedSentiment = predictedSentiment;
    }

     public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
