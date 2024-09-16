package com.example.sentiment.entiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RelaxationTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Χρησιμοποιήστε IDENTITY για αυτόματη αύξηση του ID
    private Long id;
    private String emotion;
    private String diagnosis;
    private String techniques;

    // Default constructor
    public RelaxationTechnique() {}

    // Parameterized constructor
    public RelaxationTechnique(String emotion, String diagnosis, String technique) {
        this.emotion = emotion;
        this.diagnosis = diagnosis;
        this.techniques = technique;
    }

    public String getTechnique() {
        return techniques;
    }

    public void setTechnique(String technique) {
        this.techniques = technique;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
