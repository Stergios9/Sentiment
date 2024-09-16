package com.example.sentiment.model;

import com.example.sentiment.entiy.MentalHealthProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentalHealthProfessionalRepository extends JpaRepository<MentalHealthProfessional, Long> {
    List<MentalHealthProfessional> findByLocation(String location);
}
