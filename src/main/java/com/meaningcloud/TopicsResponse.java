package com.meaningcloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create a response to Topics Extraction
 * You can find more information at https://www.meaningcloud.com/developer/topics-extraction/doc/2.0/response
 */
public class TopicsResponse extends Response {

    /**
     * Class to obtain entities or concepts
     */
    public class Topic {
        private String form;
        @SerializedName("official_form")
        private String officialForm;
        private String id;
        private int relevance;
        private SemEntity sementity;
        @SerializedName("semgeo_list")
        private List<SemGeo> semGeoList;
        @SerializedName("semld_list")
        private List<String> semldList;
        @SerializedName("semtheme_list")
        private List<Theme> semThemeList;
        @SerializedName("variant_list")
        private List<Variant> variantList;
        @SerializedName("standard_list")
        private List<StandardGeoInfo> standardList;
        private int inip;
        private int endp;

        /**
         * Form of the entity/concept in the language specified in the ilang parameter
         * @return Form of the entity/concept
         */
        public String getForm() {
            return form;
        }

        /**
         * Official form of the entity/concept, that is, its official name in cases when it's different from the form
         * @return Official form of the entity/concept
         */
        public String getOfficialForm() {
            return officialForm;
        }

        /**
         * This ID will correspond to the entity/concept senseID in resources
         * @return Alphanumeric string that univocally identifies the entity/concept
         */
        public String getId() {
            return id;
        }

        /**
         * Relative relevance of the entity in the text compared to the other entities found
         * @return Relative relevance of the entity
         */
        public int getRelevance() {
            return relevance;
        }

        /**
         * Type of entity/concept
         * @return Type of entity/concept
         * @see SemEntity
         */
        public SemEntity getSementity() {
            return sementity;
        }

        /**
         * Geographical information the entity is associated to
         * @return Geographical information the entity is associated to
         * @see SemGeo
         */
        public List<SemGeo> getSemGeoList() {
            return list(semGeoList);
        }

        /**
         * Provides a list of gateways to different open data sources
         * @return A list of gateways to different open data sources
         */
        public List<String> getSemldList() {
            return list(semldList);
        }

        /**
         * List of thematic classifications
         * @return A list of thematic classifications
         * @see Theme
         */
        public List<Theme> getSemThemeList() {
            return list(semThemeList);
        }

        /**
         * The different appearances of the entity/concept in the text
         * @return Variants of the element
         * @see Variant
         */
        public List<Variant> getVariantList() {
            return list(variantList);
        }

        /**
         * ISO standards which specify short codes for the names of countries
         * @return ISO standards which specify short codes
         * @see StandardGeoInfo
         */
        public List<StandardGeoInfo> getStandardList() {
            return list(standardList);
        }

        /**
         * Initial position of the appearance
         * @return Initial position
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the appearance
         * @return End position
         */
        public int getEndp() {
            return endp;
        }
    }

    /**
     * Class to obtain Entity
     */
    public class Entity extends Topic {}

    /**
     * Class to obtain Concept
     */
    public class Concept extends Topic {}

    /**
     * Class to obtain time expression
     */
    public class Time extends Topic {
        @SerializedName("normalized_form")
        private String normalizedForm;
        private String precision;

        /**
         * Normalized form associated to the time expression
         * @return Normalized form
         */
        public String getNormalizedForm() {
            return normalizedForm;
        }

        /**
         * Level of precision for actual_time
         * @return Level of precision
         */
        public String getPrecision() {
            return precision;
        }
    }

    /**
     * Class to obtain relation expression
     */
    public class Relation extends Topic {}

    /**
     * Class to obtain money expression
     */
    public class Money extends Topic {
        @SerializedName("amount_form")
        private String amountForm;
        @SerializedName("numeric_value")
        private String numericValue;
        private String currency;

        /**
         * Amount associated to the money expression as it appears in the text
         * @return Amount associated to the money expression
         */
        public String getAmountForm() {
            return amountForm;
        }

        /**
         * Equivalent numeric value of the amount of money
         * @return Numeric value of the amount of money
         */
        public String getNumericValue() {
            return numericValue;
        }

        /**
         * ISO4217 value associated to the currency in the money expression
         * @return ISO4217 value associated to the currency in the money expression
         */
        public String getCurrency() {
            return currency;
        }
    }

    /**
     * Class to obtain other expression
     */
    public class Other extends Topic {
        private String type;

        /**
         * Type of expression
         * @return Type of expression
         */
        public String getType() {
            return type;
        }
    }

    /**
     * Class to obtain quantity expression
     */
    public class Quantity extends Topic {
        @SerializedName("amount_form")
        private String amountForm;
        @SerializedName("numeric_value")
        private String numericValue;
        private String unit;

        /**
         * Amount associated to the quantity expression as it appears in the text
         * @return Amount associated to the quantity expression
         */
        public String getAmountForm() {
            return amountForm;
        }

        /**
         * Equivalent numeric value of the amount detected
         * @return Numeric value of the amount detected
         */
        public String getNumericValue() {
            return numericValue;
        }

        /**
         * Standarized value of the unit detected for the expression
         * @return Value of the unit
         */
        public String getUnit() {
            return unit;
        }
    }

    /**
     * Class to obtain form and lemma
     */
    public class SemLemma {
        private String form;
        private String lemma;

        /**
         * How it appears in the text
         * @return How it appears in the text
         */
        public String getForm() {
            return form;
        }

        /**
         * Lemma of the element
         * @return Lemma of the element
         */
        public String getLemma() {
            return lemma;
        }
    }

    /**
     * Class to obtain a list of quotations found in a text
     */
    public class Quotation extends Topic {
        @SerializedName("who")
        private SemLemma quote;
        private SemLemma verb;

        /**
         * Who the quote is attributed to
         * @return Who the quote is attributed to
         * @see SemLemma
         */
        public SemLemma getQuote() {
            return quote;
        }

        /**
         * Verb associated to the quotation
         * @return Verb associated to the quotation
         * @see SemLemma
         */
        public SemLemma getVerb() {
            return verb;
        }
    }

    /**
     * Class to obtain type of entity/concept
     */
    public class SemEntity {
        @SerializedName("class")
        private String semEntityClass;
        private String type;
        private String fiction;
        private String id;

        /**
         * Indicates if the node is a class or an instance in the ontology
         * @return Indicates if the node is a class or an instance in the ontology
         */
        public String getSemEntityClass() {
            return semEntityClass;
        }

        /**
         * Provides a more user-friendly notation for the type classification hierarchy of the entity
         * @return Type classification hierarchy of the entity
         */
        public String getType() {
            return type;
        }

        /**
         * Indicates if the element is fictional(fiction) or non-fictional (nonfiction)
         * @return Indicates if the element is fictional(fiction) or non-fictional (nonfiction)
         */
        public String getFiction() {
            return fiction;
        }

        /**
         * Identifier of the node associated to the entity type
         * @return Identifier of the node associated to the entity type
         */
        public String getId() {
            return id;
        }
    }

    /**
     * Class to obtain variants of the element
     */
    public class Variant {
        private String form;
        private int inip;
        private int endp;

        /**
         * How the entity/concept appears in the text
         * @return How the entity/concept appears in the text
         */
        public String getForm() {
            return form;
        }

        /**
         * Initial position of the appearance
         * @return Initial position
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the appearance
         * @return End position
         */
        public int getEndp() {
            return endp;
        }
    }

    /**
     * Class to obtain semtheme objects
     */
    public class Theme {
        private String id;
        private String type;

        /**
         * Identifier of the node associated to the theme the entity belongs to
         * @return Identifier of the node associated to the theme the entity belongs to
         */
        public String getId() {
            return id;
        }

        /**
         * Provides a more user-friendly name of all the levels of the theme classification hierarchy
         * @return Provides a more user-friendly name of all the levels of the theme classification hierarchy
         */
        public String getType() {
            return type;
        }
    }

    /**
     * Class to obtain geographical information
     */
    public class StandardGeoInfo {
        private String id;
        private String value;

        /**
         * Identifier of the geographical information
         * @return String
         */
        public String getId() {
            return id;
        }

        /**
         * Value of the geographical information
         * @return Value of the geographical information
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Class to obtain geographical information
     */
    public class CountryGeoInfo {
        private String form;
        private String id;
        @SerializedName("standard_list")
        private List<StandardGeoInfo> standardList;

        /**
         * Form of country
         * @return Form of country
         */
        public String getForm() {
            return form;
        }

        /**
         * Identifier of country
         * @return Identifier of country
         */
        public String getId() {
            return id;
        }

        /**
         * ISO standards which specify short codes for the names of countries
         * @return ISO standards which specify short codes
         * @see StandardGeoInfo
         */
        public List<StandardGeoInfo> getStandardList() {
            return list(standardList);
        }
    }

    /**
     * Class to obtain geographical information
     */
    public class Geo {
        private String form;
        private String id;

        /**
         * Form of country
         * @return Form of country
         */
        public String getForm() {
            return form;
        }

        /**
         * Identifier of country
         * @return Identifier of country
         */
        public String getId() {
            return id;
        }
    }

    /**
     * Class to obtain additional geographical information
     */
    public class SemGeo{
        private Geo adm1;
        private Geo adm2;
        private Geo continent;
        private CountryGeoInfo country;

        /**
         * Country
         * @return Country
         * @see Geo
         */
        public Geo getAdm1() {
            return adm1;
        }

        /**
         * Country
         * @return Country
         * @see Geo
         */
        public Geo getAdm2() {
            return adm2;
        }

        /**
         * Continent
         * @return Continent
         * @see Geo
         */
        public Geo getContinent() {
            return continent;
        }

        /**
         * Geographical information
         * @return Geographical information
         * @see CountryGeoInfo
         */
        public CountryGeoInfo getCountry() {
            return country;
        }
    }

    @SerializedName("entity_list")
    private List<Entity> entityList;
    @SerializedName("concept_list")
    private List<Concept> conceptList;
    @SerializedName("time_expression_list")
    private List<Time> timeExpressionList;
    @SerializedName("relation_list")
    private List<Relation> relationList;
    @SerializedName("money_expression_list")
    private List<Money> moneyExpressionList;
    @SerializedName("other_expression_list")
    private List<Other> otherExpressionList;
    @SerializedName("quantity_expression_list")
    private List<Quantity> quantityExpressionList;
    @SerializedName("quotation_list")
    private List<Quotation> quotationList;

    /**
     * List of named entities found in the input text
     * @return A list of named entities
     * @see Entity
     */
    public List<Entity> getEntityList() {
        return list(entityList);
    }

    /**
     * List of concepts found in the input text
     * @return List of concepts
     * @see Concept
     */
    public List<Concept> getConceptList() {
        return list(conceptList);
    }

    /**
     * Lists of the time expressions found in the input text
     * @return List of the time expressions
     * @see Time
     */
    public List<Time> getTimeExpressionList() {
        return list(timeExpressionList);
    }

    /**
     * List of syntactic triples (subject-action-object) found in the text
     * @return List of syntactic triples (subject-action-object)
     * @see Relation
     */
    public List<Relation> getRelationList() {
        return list(relationList);
    }

    /**
     * Lists of money expressions found in the text
     * @return Lists of money expressions
     * @see Money
     */
    public List<Money> getMoneyExpressionList() {
        return list(moneyExpressionList);
    }

    /**
     * List of unknown alphanumeric patterns found in the text
     * @return List of unknown alphanumeric patterns
     * @see Other
     */
    public List<Other> getOtherExpressionList() {
        return list(otherExpressionList);
    }

    /**
     * List of quantities found in the text
     * @return List of quantities
     * @see Quantity
     */
    public List<Quantity> getQuantityExpressionList() {
        return list(quantityExpressionList);
    }

    /**
     * List of quotations found in a text
     * @return List of quotations
     * @see Quotation
     */
    public List<Quotation> getQuotationList() {
        return list(quotationList);
    }

    /**
     * TopicsResponse factory method
     * @param response the JSON response from MeaningCloud Topics API
     * @return A topics response object
     */
    public static TopicsResponse from(String response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        TopicsResponse r = gson.fromJson(response, TopicsResponse.class);
        return r;
    }

    /**
     * Constructor
     */
    private TopicsResponse() {}
}
