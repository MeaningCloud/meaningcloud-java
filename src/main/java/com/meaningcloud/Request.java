package com.meaningcloud;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Request {

    /**
     * Language
     */
    public enum Language {
        AUTO, EN, ES, FR, IT, PT, CA;

        String code;

        /**
         * Constructor
         */
        Language() {
            this.code = null;
        }

        /**
         * Obtain language
         * @return language
         */
        public String code() {
            if (code == null) return this.name().toLowerCase();
            return code;
        }
    }

    /**
     * Interface language
     */
    public enum ILanguage {
        EN, ES, FR, IT, PT, CA;

        String code;

        /**
         * Constructor
         */
        ILanguage() {
            this.code = null;
        }

        /**
         * Obtain interface language
         * @return interface language
         */
        public String code() {
            if (code == null) return this.name().toLowerCase();
            return code;
        }
    }

    /**
     * Topic types to extract
     */
    public enum TopicType {
        NAMED_ENTITIES("e"),
        CONCEPTS("c"),
        TIME_EXPRESSIONS("t"),
        MONEY_EXPRESSIONS("m"),
        QUANTITY_EXPRESSIONS("n"),
        OTHER_EXPRESSIONS("o"),
        QUOTATIONS("q"),
        RELATIONS("r"),
        ALL("a");

        String code;

        /**
         * Constructor
         * @param code Topic types to extract
         */
        TopicType(String code) {
            this.code = code;
        }

        /**
         * Obtain topic types to extract
         * @return Topic types to extract
         */
        public String code() {
            return this.code;
        }
    }

    /**
     * Text format
     */
    public enum TextFormat {
        PLAIN, MARKUP;

        /**
         * Obtain text format
         * @return Text format
         */
        public String code() {
            return this.name().toLowerCase();
        }
    }

    /**
     * Deal with relaxed typography
     */
    public enum RelaxedTypography {
        ENABLED("y"), ENABLED_FOR_USER_DICTIONARY("u"), DISABLED("n");

        String code;

        /**
         * Constructor
         * @param code Relaxed typography
         */
        RelaxedTypography(String code) {
            this.code = code;
        }

        /**
         * Obtain relaxed typography
         * @return Relaxed typography
         */
        public String code() {
            return this.code;
        }
    }

    /**
     * Type of disambiguation applied
     */
    public enum DisambiguationLevel {
        NONE("n"),
        MORPHOSYNTACTIC("m"),
        SEMANTIC("s");

        String code;

        /**
         * Constructor
         * @param code Type of disambiguation applied
         */
        DisambiguationLevel(String code) {
            this.code = code;
        }

        /**
         * Obtain type of disambiguation applied
         * @return Disambiguation applied
         */
        public String code() {
            return this.code;
        }
    }

    /**
     * Semantic disambiguation grouping
     */
    public enum SemanticDisambiguationGrouping {
        NONE("n"),
        BY_TYPE("t"),
        BY_TYPE_SMALLEST_LOCATION("l"),
        GLOBAL_INTERSECTION("g");

        String code;

        /**
         * Constructor
         * @param code Semantic disambiguation grouping
         */
        SemanticDisambiguationGrouping(String code) {
            this.code = code;
        }

        /**
         * Obtain semantic disambiguation grouping
         * @return Semantic disambiguation grouping
         */
        public String code() {
            return this.code;
        }
    }

    /**
     * Class to obtain a validation exception
     */
    public class ParameterValidationException extends Exception {

        /**
         * Constructor
         * @param message Error message
         */
        public ParameterValidationException(String message) {
            super(message);
        }
    }

    protected final String key;
    public static final String SERVER = "https://api.meaningcloud.com/";

    /**
     * Constructor
     * @param key User's API key
     */
    protected Request(String key) {
        this.key = key;
    }

    /**
     * Post to request
     * @param endpoint API server
     * @param params Parameters to API call
     * @return A string with API call
     * @throws IOException Raised when a parameter value can't be accepted
     */
    protected String post(String endpoint, Map<String, String> params) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(endpoint);
        post.setHeader("User-Agent", "MeaningCloud Java client");
        List<NameValuePair> urlParameters = new ArrayList<>();
        for (Map.Entry<String, String> x : params.entrySet()) {
            urlParameters.add(new BasicNameValuePair(x.getKey(), x.getValue()));
        }
        UrlEncodedFormEntity x = new UrlEncodedFormEntity(urlParameters, Consts.UTF_8);
        x.setContentEncoding("utf-8");
        post.setEntity(x);

        CloseableHttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
