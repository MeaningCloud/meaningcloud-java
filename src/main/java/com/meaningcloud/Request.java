package com.meaningcloud;

import java.io.IOException;
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

    public static final Transport DEFAULT_TRANSPORT = new HTTP();

    protected final String key;
    protected final Transport transport;

    /**
     * Constructor
     * @param transport
     * @param key User's API key
     * @see Transport
     */
    protected Request(Transport transport, String key) {
        this.transport = transport;
        this.key = key;
    }
}
