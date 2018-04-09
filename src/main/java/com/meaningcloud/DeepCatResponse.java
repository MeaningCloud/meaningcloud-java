package com.meaningcloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create a response to Deep Categorization
 * You can find more information at https://www.meaningcloud.com/developer/deep-categorization/doc/1.0/response
 */
public class DeepCatResponse extends  Response {

    /**
     * Class to obtain a list of categories
     * in which the input text is classified
     */
    public class Category {
        private String code;
        private String label;
        @SerializedName("abs_relevance")
        private float absRelevance;
        private float relevance;
        private String polarity;
        @SerializedName("term_list")
        private List<Term> termList;

        /**
         * Category code
         * @return A string category code
         */
        public String getCode() {
            return code;
        }

        /**
         * Category description
         * @return A string with category description
         */
        public String getLabel() {
            return label;
        }

        /**
         * Absolute relevance value of the category
         * @return A float with absolute relevance value
         */
        public float getAbsRelevance() {
            return absRelevance;
        }

        /**
         * Relative relevance value of the category
         * @return A float with relative relevance value
         */
        public float getRelevance() {
            return relevance;
        }

        /**
         * Polarity associated to the category
         * @return A string with the polarity
         */
        public String getPolarity() {
            return polarity;
        }

        /**
         * List of the relevant terms of the category
         * @return A list of the relevant terms
         * @see Term
         */
        public List<Term> getTermList() {
            return termList;
        }
    }

    /**
     * Class to obtain a list of terms
     */
    public class Term {
        private String form;
        @SerializedName("abs_relevance")
        private float absRelevance;
        @SerializedName("offset_list")
        private List<Offsets> offsetList;

        /**
         * Value of the term
         * @return A string with value of the term
         */
        public String getForm() {
            return form;
        }

        /**
         * Numeric value of how the term affects the absolute relevance
         * @return A float with value of how the term affects the absolute relevance
         */
        public float getAbsRelevance() {
            return absRelevance;
        }

        /**
         * List of offsets
         * @return A list of offsets
         * @see Offsets
         */
        public List<Offsets> getOffsetList() {
            return offsetList;
        }
    }

    /**
     * Class to obtain a list of offsets
     */
    public class Offsets {
        private float inip;
        private float endp;

        /**
         * Initial position of the term
         * @return A float with initial position of the term
         */
        public float getInip() {
            return inip;
        }

        /**
         * End position fo the term
         * @return A float with end position of the term
         */
        public float getEndp() {
            return endp;
        }
    }

    @SerializedName("category_list")
    private List<Category> categoryList;

    /**
     * List of categories in which the input text is classified
     * @return A list of categories
     */
    public List<Category> getCategoryList() {
        return categoryList;
    }


    /**
     * DeepCatResponse factory method
     * @param response the JSON response from MeaningCloud Deep Categorization API
     * @return
     */
    public static DeepCatResponse from(String response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        DeepCatResponse r = gson.fromJson(response, DeepCatResponse.class);
        return r;
    }

    /**
     * Constructor
     */
    private DeepCatResponse() {}
}
