package com.example.sentiment.service;

import com.example.sentiment.utils.SentimentPhrasalVerbs;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PhrasalVerbService {

    private SentimentPhrasalVerbs sentimentPhrasalVerbs = new SentimentPhrasalVerbs();

    public int processPhrasalVerb(String phrasalVerb, List<String> sentimentWords, boolean negateNext) {
        String modifiedPhrasalVerb = phrasalVerb;

        if (negateNext){
            modifiedPhrasalVerb = "not "+phrasalVerb;
        }

        if (sentimentPhrasalVerbs.getAngerPhrasalVerbs().contains(phrasalVerb)) {
            sentimentWords.add(modifiedPhrasalVerb);
            return 1;
        } else if (sentimentPhrasalVerbs.getJoyPhrasalVerbs().contains(phrasalVerb)) {
            sentimentWords.add(modifiedPhrasalVerb);
            return 2;
        } else if (sentimentPhrasalVerbs.getFearPhrasalVerbs().contains(phrasalVerb)) {
            sentimentWords.add(modifiedPhrasalVerb);
            return 3;
        } else if (sentimentPhrasalVerbs.getAnxietyPhrasalVerbs().contains(phrasalVerb)) {
            sentimentWords.add(modifiedPhrasalVerb);
            return 4;
        } else if (sentimentPhrasalVerbs.getSadnessPhrasalVerbs().contains(phrasalVerb)) {
            sentimentWords.add(modifiedPhrasalVerb);
            return 5;
        }

        return 0;
    }

}
