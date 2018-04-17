package com.meaningcloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create a response to Language Identification
 * You can find more information at https://www.meaningcloud.com/developer/language-identification/doc/2.0/response
 */
public class LangResponse extends Response{

    @SerializedName("language_list")
    private List<Language> languageList;

    /**
     * List of detected languages for the document sent
     * @return A list of detected languages
     */
    public List<Language> getLanguageList() {
        return list(languageList);
    }

    /**
     * Class to obtain a list of detected languages
     */
    public class Language {
        private String language;
        private int relevance;
        private String name;
        @SerializedName("iso639-3")
        private String iso3;
        @SerializedName("iso639-2")
        private String iso2;
        private String script;
        private int speakers;

        /**
         * Language detected using the code defined by the ISO639-1 standard
         * @return A string with language detected
         */
        public String getLanguage() {
            return language;
        }

        /**
         * Relevance value associated to it
         * @return int with relevance value
         */
        public int getRelevance() {
            return relevance;
        }

        /**
         * Name of the language
         * @return A string with name of the language
         */
        public String getName() {
            return name;
        }

        /**
         * Three letter code of the ISO639-1 standard
         * @return A string with three letter code
         */
        public String getIso3() {
            return iso3;
        }

        /**
         * Two letter code of the ISO639-1 standard
         * @return A string with two letter code
         */
        public String getIso2() {
            return iso2;
        }

        /**
         * Script used by the language. It will only appear
         * when the verbose parameter is enabled
         * @return A string with script used by the language
         */
        public String getScript() {
            return script;
        }

        /**
         * Number of speakers. It will only appear
         * when the verbose parameter is enabled
         * @return int with number of speakers
         */
        public int getSpeakers() {
            return speakers;
        }
    }

    /**
     * LangResponse factory method
     * @param response the JSON response from MeaningCloud Lang API
     * @return
     */
    public static LangResponse from(String response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        LangResponse r = gson.fromJson(response, LangResponse.class);
        return r;
    }

    /**
     * Constructor
     */
    private LangResponse() {}

}
