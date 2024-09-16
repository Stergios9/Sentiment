package com.example.sentiment.service;

import com.example.sentiment.entiy.Review;
import com.example.sentiment.entiy.Sentiment;
import com.example.sentiment.utils.SentimentWords;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SentimentService {

    @Autowired
    private PhrasalVerbService phrasalVerbService;

    public static final Set<String> NEGATION_WORDS = new HashSet<>(Arrays.asList(
            "not", "never", "no", "none", "hardly", "barely", "rarely", "seldom", "without", "neither"
    ));


    public Sentiment analyzeSentiment(String comment, Review review) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument(comment);
        pipeline.annotate(document);

        int joyfulCount = 0;
        int fearfulCount = 0;
        int anxiousCount = 0;
        int angryCount = 0;
        int sadnessCount = 0;
        int neutralCount = 0;
        int totalWords = 0;
        int numberOf_sentimentWords = 0;

        boolean negateNext = false;
        int negateWindow = 0;
        int negateDistance = 3; // Distance from negation to apply it
        List<String> sentimentWords = new ArrayList<>();

        for (CoreSentence sentence : document.sentences()) {
            List<CoreLabel> tokens = sentence.tokens();
            for (int i = 0; i < tokens.size(); i++) {
                String word = tokens.get(i).lemma().toLowerCase();
                String pos = tokens.get(i).get(CoreAnnotations.PartOfSpeechAnnotation.class);
                System.out.println("\n\nToken: " + word + ", POS: " + pos+"\n");

                // Check if the token is punctuation or a number
                if (word.matches("\\p{Punct}") || word.matches("\\d+")) {
                    // Reset negation if punctuation is a sentence-ending punctuation
                    if (word.equals(".") || word.equals("!") || word.equals("?")) {
                        negateNext = false;
                    }
                    continue;
                }

                // Count total words (excluding punctuation and numbers)
                totalWords++;

                // Reset negateWindow if its counter expires
                if (negateWindow > 0) {
                    negateWindow--;
                }

                // Check for negation word and reset negateWindow
                if (NEGATION_WORDS.contains(word)) {
                    negateWindow = negateDistance;
                    negateNext = true;
                    continue;
                }

                // Handle Phrasal Verbs
                boolean phrasalVerbFound = false;

                for (int j = 1; j <= 3 && (i + j) < tokens.size(); j++) {
                    String nextWord = tokens.get(i + j).lemma().toLowerCase();
                    String phrasalVerb = word + " " + nextWord;

                    int result = phrasalVerbService.processPhrasalVerb(phrasalVerb, sentimentWords,negateNext);
                    System.out.println("sentimentWords after "+j+" entrance in PhrasaVerbService: " + sentimentWords);

                    if (result != 0) {
                        if (negateNext) {
                            result = invertSentiment(result);
                            negateNext = false; // Reset negation flag after applying
                        }
                        switch (result) {
                            case 1 -> angryCount++;
                            case 2 -> joyfulCount++;
                            case 3 -> fearfulCount++;
                            case 4 -> anxiousCount++;
                            case 5 -> sadnessCount++;
                        }
                        numberOf_sentimentWords++;
                        i += j; // Move index to skip the words part of the phrasal verb
                        phrasalVerbFound = true;
                        break;
                    }
                }

                if (phrasalVerbFound) {
                    negateNext = false; // Reset negation flag after handling a phrasal verb
                    continue;
                }

                // Skip neutral words (e.g., "always") if negation is active
                if (negateWindow > 0 && !SentimentWords.isSentimentWord(word)) {
                    continue;
                }

                // Apply sentiment logic
                if (SentimentWords.JOYFUL_WORDS.contains(word)) {
                    if (negateNext) {
                        sadnessCount++;
                        negateNext = false;
                        sentimentWords.add("not "+word);
                    } else {
                        joyfulCount++;
                        sentimentWords.add(word);
                    }
                    numberOf_sentimentWords++;
                } else if (SentimentWords.SADNESS_WORDS.contains(word)) {
                    if (negateNext) {
                        joyfulCount++;
                        negateNext = false;
                        sentimentWords.add("not "+word);
                    } else {
                        sadnessCount++;
                        sentimentWords.add(word);
                    }
                    numberOf_sentimentWords++;
                } else if (SentimentWords.ANXIOUS_WORDS.contains(word)) {
                    if (negateNext) {
                        joyfulCount++;
                        negateNext = false;
                        sentimentWords.add("not "+word);
                    } else {
                        anxiousCount++;
                        sentimentWords.add(word);
                    }
                    numberOf_sentimentWords++;
                } else if (SentimentWords.ANGRY_WORDS.contains(word)) {
                    if (negateNext) {
                        joyfulCount++;
                        negateNext = false;
                        sentimentWords.add("not "+word);
                    } else {
                        angryCount++;
                        sentimentWords.add(word);
                    }
                    numberOf_sentimentWords++;
                } else if (SentimentWords.FEARING_WORDS.contains(word)) {
                    if (negateNext) {
                        joyfulCount++;
                        negateNext = false;
                        sentimentWords.add("not "+word);
                    } else {
                        fearfulCount++;
                        sentimentWords.add(word);
                    }
                    numberOf_sentimentWords++;
                }
            }
        }

        System.out.println("\n\n********** Statistics **********");
        System.out.println("\nTotalwords: " + totalWords);
        System.out.println("\nnumberOf_sentimentWords: " + numberOf_sentimentWords);
        System.out.println("\nsentimentWords: " + sentimentWords);

        // Avoid division by zero
        if (totalWords == 0) totalWords = 1;

        // Calculate scores
        double joyScore = joyfulCount / (double) totalWords;
        double fearScore = fearfulCount / (double) totalWords;
        double anxiousScore = anxiousCount / (double) totalWords;
        double sadnessScore = sadnessCount / (double) totalWords;
        double angerScore = angryCount / (double) totalWords;
        double neutralScore = neutralCount / (double) totalWords;

        System.out.println("\njoyScore: " + joyScore + "\n");
        System.out.println("\nfearScore: " + fearScore + "\n");
        System.out.println("\nanxiousScore: " + anxiousScore + "\n");
        System.out.println("\nsadnessScore: " + sadnessScore + "\n");
        System.out.println("\nangerScore: " + angerScore + "\n");
        System.out.println("\nneutralScore: " + neutralScore + "\n");

        // Create a Map to store the scores
        Map<String, Double> emotionScores = new HashMap<>();
        if (joyScore >= 0.01) {
            emotionScores.put("joy", joyScore);
        }
        if (fearScore >= 0.01) {
            emotionScores.put("fear", fearScore);
        }
        if (anxiousScore >= 0.01) {
            emotionScores.put("anxiety", anxiousScore);
        }
        if (sadnessScore >= 0.01) {
            emotionScores.put("sadness", sadnessScore);
        }
        if (angerScore >= 0.01) {
            emotionScores.put("anger", angerScore);
        }
        if (neutralScore >= 0.01) {
            emotionScores.put("neutral", neutralScore);
        }
        if (joyScore < 0.01 && fearScore < 0.01 && anxiousScore < 0.01 && sadnessScore < 0.01 && angerScore < 0.01) {
            emotionScores.put("neutral", 1.0);
        }

        // Generate composite sentiment by sorting the keys (emotions) alphabetically
        String compositeSentiment = emotionScores.keySet().stream()
                .sorted()  // Sort emotions alphabetically
                .collect(Collectors.joining("_"));

        System.out.println("\ncompositeSentiment: " + compositeSentiment+"\n\n");

        return new Sentiment(compositeSentiment, review);
    }

    private int invertSentiment(int sentiment) {
        // Invert the sentiment score based on negation
        switch (sentiment) {
            case 1: // Angry
                return 2; // Joyful
            case 2: // Joyful
                return 1; // Angry
            case 3: // Fearful
                return 4; // Anxious
            case 4: // Anxious
                return 3; // Fearful
            case 5: // Sadness
                return 2; // Joyful
            default:
                return 0;
        }
    }
}
