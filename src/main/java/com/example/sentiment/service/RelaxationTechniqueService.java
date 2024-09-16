package com.example.sentiment.service;

import com.example.sentiment.entiy.RelaxationTechnique;
import com.example.sentiment.model.RelaxationTechniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelaxationTechniqueService {
    @Autowired
    private RelaxationTechniqueRepository relaxationTechniqueRepository;

    public RelaxationTechnique getTechniqueByEmotion(String emotion) {
        // Επιστρέφει ένα αντικείμενο RelaxationTechnique
        return relaxationTechniqueRepository.findRelaxationTechniqueByEmotion(emotion);
    }
}
