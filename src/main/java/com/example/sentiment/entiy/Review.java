package com.example.sentiment.entiy;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Review {

    @Id//@Entity: This annotation specifies that the class is an entity and is mapped to a database table.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String location;
    private String comment;
    private LocalDateTime timestamp;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Sentiment sentiment;

    // Default constructor
    public Review() {}

    // Parameterized constructor
    public Review(String firstName, String lastName, String location, String comment, LocalDateTime timestamp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    public void setSentiment(Sentiment sentiment) {
        this.sentiment = sentiment;
        sentiment.setReview(this);  // Update the relationship on the other side
    }
}
