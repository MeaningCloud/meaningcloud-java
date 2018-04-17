package com.meaningcloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create a response to Sentiment Analyze
 * You can find more information at https://www.meaningcloud.com/developer/sentiment-analysis/doc/2.1/response
 */
public class SentimentResponse extends Response {

    /**
     * Class to obtain sentence object
     */
    public class Sentence {
        private String text;
        private int inip;
        private int endp;
        private String bop;
        private int confidence;
        @SerializedName("score_tag")
        private String scoreTag;
        private String agreement;
        @SerializedName("segment_list")
        private List<Segment> segmentList;
        @SerializedName("sentimented_entity_list")
        private List<Sentimented> sentimentedEntityList;
        @SerializedName("sentimented_concept_list")
        private List<Sentimented> sentimentedConceptList;

        /**
         * Text of the sentence
         * @return Text of the sentence
         */
        public String getText() {
            return text;
        }

        /**
         * Initial position of the sentence
         * @return Initial position of the sentence
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the sentence
         * @return End position of the sentence
         */
        public int getEndp() {
            return endp;
        }

        /**
         * Marks if the sentence is the of beginning of the paragraph
         * @return Marks if the sentence is the of beginning of the paragraph
         */
        public String getBop() {
            return bop;
        }

        /**
         * This field represents the confidence associated with the sentiment analysis performed on the text
         * @return Integer number in the 0-100 range
         */
        public int getConfidence() {
            return confidence;
        }

        /**
         * This tag indicates the polarity found (or not found) in the element it refers to
         * @return Polarity
         */
        public String getScoreTag() {
            return scoreTag;
        }

        /**
         * This field marks the agreement between the sentiments detected in the sentence
         * @return Agreement between the sentiments
         */
        public String getAgreement() {
            return agreement;
        }

        /**
         * List of segments in which each sentence has been divided to perform the analysis
         * @return List of segments
         * @see Segment
         */
        public List<Segment> getSegmentList() {
            return list(segmentList);
        }

        /**
         * This is a list of the entities identified in the sentence
         * @return A list of the entities
         * @see Sentimented
         */
        public List<Sentimented> getSentimentedEntityList() {
            return list(sentimentedEntityList);
        }

        /**
         * This is a list of the concepts identified in the sentence
         * @return A list of the concepts
         * @see Sentimented
         */
        public List<Sentimented> getSentimentedConceptList() {
            return list(sentimentedConceptList);
        }
    }

    /**
     * Class to obtain a list of the entities or concepts identified in the sentence
     */
    public class Sentimented {
        private String form;
        private String id;
        private String variant;
        private int inip;
        private int endp;
        private String type;
        @SerializedName("score_tag")
        private String scoreTag;

        /**
         * Main form of the entity/concept in the language specified in the ilang parameter
         * @return Form of the entity/concept
         */
        public String getForm() {
            return form;
        }

        /**
         * ID of the entity/concept
         * @return ID of the entity/concept
         */
        public String getId() {
            return id;
        }

        /**
         * How the entity/concept appears in the text
         * @return How the entity/concept appears in the text
         */
        public String getVariant() {
            return variant;
        }

        /**
         * Position in which the entity/concept begins
         * @return Initial position of the entity/concept
         */
        public int getInip() {
            return inip;
        }

        /**
         * Position in which the entity/concept ends
         * @return End position of the entity/concept
         */
        public int getEndp() {
            return endp;
        }

        /**
         * Ontology type of the entity/concept
         * @return Ontology type of the entity/concept
         */
        public String getType() {
            return type;
        }

        /**
         * This tag indicates the polarity found (or not found) in the element it refers to
         * @return Polarity found
         */
        public String getScoreTag() {
            return scoreTag;
        }
    }

    /**
     * Class to obtain polarity
     */
    public class Polarity {
        private String text;
        private int inip;
        private int endp;
        private int confidence;
        @SerializedName("score_tag")
        private String scoreTag;
        @SerializedName("sentimented_entity_list")
        private List<Sentimented> sentimentedEntityList;
        @SerializedName("sentimented_concept_list")
        private List<Sentimented> sentimentedConceptList;
        @SerializedName("tag_stack")
        private String tagStack;

        /**
         * Text of the term
         * @return Text of the term
         */
        public String getText() {
            return text;
        }

        /**
         * Position in which the polarity term begins
         * @return Initial position of the polarity
         */
        public int getInip() {
            return inip;
        }

        /**
         * Position in which the polarity term ends
         * @return End position of the polarity
         */
        public int getEndp() {
            return endp;
        }

        /**
         * This field represents the confidence associated with the sentiment analysis performed on the text
         * @return Its value is an integer number in the 0-100 range
         */
        public int getConfidence() {
            return confidence;
        }

        /**
         * This tag indicates the polarity found (or not found) in the element it refers to
         * @return Polarity found
         */
        public String getScoreTag() {
            return scoreTag;
        }

        /**
         * A list of entities affected by the polarity term
         * @return A list of entities
         * @see Sentimented
         */
        public List<Sentimented> getSentimentedEntityList() {
            return list(sentimentedEntityList);
        }

        /**
         * A list of concepts affected by the polarity term
         * @return A list of concepts
         * @see Sentimented
         */
        public List<Sentimented> getSentimentedConceptList() {
            return list(sentimentedConceptList);
        }

        /**
         * Polarity modifiers affecting this polarity term
         * @return Polarity modifiers
         */
        public String getTagStack() {
            return tagStack;
        }
    }

    /**
     * Class to obtain a segment object
     */
    public class Segment {
        private String text;
        @SerializedName("segment_type")
        private String segmentType;
        private int inip;
        private int endp;
        private int confidence;
        @SerializedName("score_tag")
        private String scoreTag;
        private String agreement;
        @SerializedName("polarity_term_list")
        private List<Polarity> polarityTermList;
        @SerializedName("sentimented_concept_list")
        private List<Concept> sentimentedConceptList;

        /**
         * Text of the segment
         * @return Text of the segment
         */
        public String getText() {
            return text;
        }

        /**
         * This field indicates if the segment has been used to compute the aggregated polarity of its parent
         * @return Polarity
         */
        public String getSegmentType() {
            return segmentType;
        }

        /**
         * Position in which the segment begins
         * @return Initial position of the segment
         */
        public int getInip() {
            return inip;
        }

        /**
         * Position in which the segment ends
         * @return End position of the segment
         */
        public int getEndp() {
            return endp;
        }

        /**
         * This field represents the confidence associated with the sentiment analysis performed on the text
         * @return Its value is an integer number in the 0-100 range
         */
        public int getConfidence() {
            return confidence;
        }

        /**
         * This tag indicates the polarity found (or not found) in the element it refers to
         * @return Polarity found
         */
        public String getScoreTag() {
            return scoreTag;
        }

        /**
         * This field marks the agreement between the sentiments detected in the segment it refers to
         * @return The agreement between the sentiments detected in the segment
         */
        public String getAgreement() {
            return agreement;
        }

        /**
         * List of words with polarity found in the segment
         * @return A list of words with polarity found in the segment
         * @see Polarity
         */
        public List<Polarity> getPolarityTermList() {
            return list(polarityTermList);
        }

        /**
         * This is a list of the concepts identified in the segment but that are not affected by the polarity terms identified in it
         * @return A list of the concepts identified in the segment
         * @see Concept
         */
        public List<Concept> getSentimentedConceptList() {
            return list(sentimentedConceptList);
        }
    }

    /**
     * Class to obtain concepts that are detected in the text
     */
    public class Concept {
        private String form;
        private String id;
        private String variant;
        private int inip;
        private int endp;
        private String type;
        @SerializedName("score_tag")
        private String scoreTag;

        /**
         * Main form of the concept in the language specified in the ilang parameter
         * @return A string with the form of the concept
         */
        public String getForm() {
            return form;
        }

        /**
         * ID of the concept
         * @return ID of the concept
         */
        public String getId() {
            return id;
        }

        /**
         * How the concept appears in the text
         * @return How the concept appears in the text
         */
        public String getVariant() {
            return variant;
        }

        /**
         * Position in which the concept begins
         * @return Initial position of the concept
         */
        public int getInip() {
            return inip;
        }

        /**
         * Position in which the concept ends
         * @return End position of the concept
         */
        public int getEndp() {
            return endp;
        }

        /**
         * Ontology type of the concept
         * @return Ontology type of the concept
         */
        public String getType() {
            return type;
        }

        /**
         * This tag indicates the polarity found (or not found) in the element it refers to
         * @return Polarity found
         */
        public String getScoreTag() {
            return scoreTag;
        }
    }

    @SerializedName("sentence_list")
    private List<Sentence> sentenceList;

    /**
     * List of sentences in which the text is divided
     * @return A list of sentences in which the text is divided
     * @see Sentence
     */
    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    @SerializedName("sentimented_entity_list")
    private List<Sentimented> sentimentedEntityList;

    /**
     * This is a list of the entities identified in the text with a certain polarity
     * @return A list of the entities identified in the text
     * @see Sentimented
     */
    public List<Sentimented> getSentimentedEntityList() {
        return list(sentimentedEntityList);
    }

    @SerializedName("sentimented_concept_list")
    private List<Sentimented> sentimentedConceptList;

    /**
     * This is a list of the concepts identified in the text with a certain polarity
     * @return A list of the concepts identified in the text
     */
    public List<Sentimented> getSentimentedConceptList() {
        return list(sentimentedConceptList);
    }

    private String model;
    @SerializedName("score_tag")
    private String scoreTag;
    private String agreement;
    private String subjectivity;
    private int confidence;
    private String irony;

    /**
     * This field holds the model used in the evaluation followed by an underscore
     * and the language in which the analysis has been carried out
     * @return A model used in the evaluation
     */
    public String getModel() {
        return model;
    }

    /**
     * This tag indicates the polarity found (or not found) in the element it refers to
     * @return Polarity found
     */
    public String getScoreTag() {
        return scoreTag;
    }

    /**
     * This field marks the agreement between the sentiments detected in the text
     * @return Agreement between the sentiments
     */
    public String getAgreement() {
        return agreement;
    }

    /**
     * This field marks the subjectivity of the text
     * @return The subjectivity of the text
     */
    public String getSubjectivity() {
        return subjectivity;
    }

    /**
     * This field represents the confidence associated with the sentiment analysis performed on the text
     * @return The confidence associated with the sentiment analysis performed on the text
     */
    public int getConfidence() {
        return confidence;
    }

    /**
     * This field indicates the irony of the text
     * @return The irony of the text
     */
    public String getIrony() {
        return irony;
    }

    /**
     * SentimentResponse factory method
     * @param response the JSON response from MeaningCloud Sentiment API
     * @return A sentiment response object
     */
    public static SentimentResponse from(String response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        SentimentResponse r = gson.fromJson(response, SentimentResponse.class);
        return r;
    }

    /**
     * Constructor
     */
    private SentimentResponse() {}
}
