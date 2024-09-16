package com.example.sentiment.utils;

import java.util.HashMap;
import java.util.Map;

public class SentimentDiagnoses {
    public static final Map<String, String> DIAGNOSES = new HashMap<>();

    static {
        DIAGNOSES.put("positive", "Your psychological state is calm and positive. Continue doing things that make you happy and maintain a positive mindset. Engage in activities you love, spend time with loved ones, and practice gratitude daily to sustain your positive outlook.");
        DIAGNOSES.put("negative", "The emotions you're experiencing are driving your overall psychology to negative levels. We recommend speaking with a mental health professional to receive help. Additionally, try to identify and address the sources of your negative feelings. Consider incorporating mindfulness practices, regular exercise, and a healthy diet to improve your mood. Here you will find a list of experts in psychology who can offer their services.");
        DIAGNOSES.put("neutral", "Your mental health is at a stable level. To enhance it and feel better, we suggest exploring ways to improve it and find more enjoyment in your daily life. Engage in activities that bring you joy, explore new hobbies, and connect with friends and family. Here you will find some ideas on how to achieve this.");
        DIAGNOSES.put("anxious", "The emotion you're experiencing is anxiety. It is recommended to use relaxation techniques such as deep breathing, meditation, or yoga. Seek support from a mental health professional to explore coping strategies and possibly consider cognitive-behavioral therapy. Regular physical activity and a balanced diet can also help manage anxiety levels.");
        DIAGNOSES.put("angry", "The emotion you're experiencing is anger. It is important to manage your anger in healthy ways such as through physical activity, creative outlets, or speaking with a therapist. Practice deep breathing exercises and mindfulness to calm down when you feel anger rising. Talking to a professional can provide you with tools and strategies to handle anger constructively.");
        DIAGNOSES.put("joyful", "Your diagnosis is joy. Continue enjoying moments of happiness and maintain your positive energy. Share your joy with others, engage in activities that bring you pleasure, and practice self-care to nurture your well-being. Keep setting personal goals that inspire you and maintain social connections that uplift your spirit.");
    }


}
