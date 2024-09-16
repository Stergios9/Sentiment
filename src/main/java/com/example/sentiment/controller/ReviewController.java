package com.example.sentiment.controller;

import com.example.sentiment.entiy.Review;
import com.example.sentiment.entiy.RelaxationTechnique;
import com.example.sentiment.entiy.MentalHealthProfessional;
import com.example.sentiment.model.MentalHealthProfessionalRepository;
import com.example.sentiment.service.MentalHealthProfessionalService;
import com.example.sentiment.service.RelaxationTechniqueService;
import com.example.sentiment.service.ReviewService;
import com.example.sentiment.service.SentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RelaxationTechniqueService relaxationTechniqueService;

    @Autowired
    private MentalHealthProfessionalService mentalHealthProfessionalService;

    @PostMapping("/reviews/submit")
    public String submitReview(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("location") String location,
                               @RequestParam("comment") String comment,
                               Model model) {
        // Δημιουργία και αποθήκευση του review
        Review review = reviewService.submitReview(firstName, lastName, location, comment);

        // Ανάλυση συναισθήματος
        String emotion = review.getSentiment().getPredictedSentiment();

        // Εύρεση τεχνικών χαλάρωσης και επαγγελματιών ψυχικής υγείας
        RelaxationTechnique technique = relaxationTechniqueService.getTechniqueByEmotion(emotion);
        List<MentalHealthProfessional> professionals = mentalHealthProfessionalService.getProfessionalsByLocation(location);

        // Προσθήκη δεδομένων στο μοντέλο
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("location", location);
        model.addAttribute("comment", comment);
        model.addAttribute("emotion", emotion);
        model.addAttribute("diagnosis", technique.getDiagnosis());
        model.addAttribute("techniques", technique.getTechnique());
        model.addAttribute("professionals", professionals);

        return "result";
    }
}
