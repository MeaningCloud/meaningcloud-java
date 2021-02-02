package com.meaningcloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create a response to Text Classification
 * You can find more information at https://www.meaningcloud.com/developer/text-classification/doc/2.0/response
 */
public class ClassResponse extends Response {

    /**
     * Class to obtain categories
     */
    public class Category {
        private String code;
        private String label;
        @SerializedName("abs_relevance")
        private float absRelevance;
        private float relevance;
        @SerializedName("term_list")
        private List<Term> termList;

        /**
         * Category code
         * @return A string with category code
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
         * @return A float that indicates absolute relevance value
         */
        public float getAbsRelevance() {
            return absRelevance;
        }

        /**
         * Relative relevance value of the category
         * @return A float in the 0-100% range
         */
        public float getRelevance() {
            return relevance;
        }

        /**
         * List of the relevant terms of the category
         * @return A list of the relevant terms of the category
         * @see Term
         */
        public List<Term> getTermList() {
            return list(termList);
        }
    }

    /**
     * Class to obtain terms of the category
     */
    public class Term {
        private String form;
        @SerializedName("abs_relevance")
        private float absRelevance;

        /**
         * Field associated to the term
         * @return A string with form of the term
         */
        public String getForm() {
            return form;
        }

        /**
         * Absolute relevance value of the category
         * @return A float which relative relevance value of the category
         */
        public float getAbsRelevance() {
            return absRelevance;
        }
    }

    @SerializedName("category_list")
    private List<Category> categoryList;

    /**
     * List of the categories
     * @return A list of the categories
     * @see Category
     */
    public List<Category> getCategoryList() {
        return list(categoryList);
    }


    /**
     * ClassResponse factory method
     * @param response the JSON response from MeaningCloud Text Classification API
     * @return ClassResponse
     */
    public static ClassResponse from(String response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ClassResponse r = gson.fromJson(response, ClassResponse.class);
        return r;
    }

    /**
     * Constructor
     */
    private ClassResponse(){}
}
