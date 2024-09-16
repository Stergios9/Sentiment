package com.example.sentiment.service;

import com.example.sentiment.entiy.MentalHealthProfessional;
import com.example.sentiment.model.MentalHealthProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentalHealthProfessionalService {
    @Autowired
    private MentalHealthProfessionalRepository repository;

    public List<MentalHealthProfessional> getProfessionalsByLocation(String location) {
        return repository.findByLocation(location);
    }
}