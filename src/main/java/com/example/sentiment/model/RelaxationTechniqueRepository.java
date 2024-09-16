package com.example.sentiment.model;

import com.example.sentiment.entiy.RelaxationTechnique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelaxationTechniqueRepository extends JpaRepository<RelaxationTechnique, Long> {
    RelaxationTechnique findRelaxationTechniqueByEmotion(String emotion);

    boolean existsByEmotion(String combination);
}
