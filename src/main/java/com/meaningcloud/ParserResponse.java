package com.meaningcloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.meaningcloud.visitor.ParserResponseVisitor;

import java.util.*;

/**
 * Create a response to Lemmatization, PoS and Parsing
 * You can find more information at https://www.meaningcloud.com/developer/topics-extraction/doc/2.0/response
 */
public class ParserResponse extends Response {

    /**
     * Auxiliary class to obtain lemmatization
     */
    public class Lemma {
        private final String lemma;
        private final String tag;
        private final Token token;

        /**
         * Constructor
         * @param lemma Lemma associated to the analysis
         * @param tag Morphosyntactic tag associated to the token
         */
        public Lemma(String lemma, String tag, Token token) {
            this.lemma = lemma;
            this.tag = tag;
            this.token = token;
        }

        /**
         * Lemma associated to the analysis
         * @return Lemma associated to the analysis
         */
        public String getLemma() {
            return lemma;
        }

        /**
         * Morphosyntactic tag associated to the token
         * @return Morphosyntactic tag associated to the token
         */
        public String getTag() {
            return tag;
        }

        /**
         * The token this lemma relates to
         * @return Token
         */
        public Token getToken() {
            return token;
        }
    }

    /**
     * The method used for creating the lemmatization
     * @return A List of lemmas
     */
    public List<Lemma> lemmatize () {
        List<Token> leaves = new LinkedList<>();
        List<Lemma> lemmas = new LinkedList<>();

        class LeavesCollector extends ParserResponseVisitor {
            @Override
            public void visit(Token token) {
                List<Token> x = token.getTokenList();
                if (x == null || x.size() == 0 ) {
                    leaves.add(token);
                }
                super.visit(token);
            }
        }

        new LeavesCollector().visit(this);

        for (Token t : leaves) {
            List<Analysis> analyses = t.getAnalysisList();
            Lemma l;
            if (analyses != null && analyses.size() > 0){
                Analysis a  = analyses.get(0);
                String lemma = a.getLemma();
                String tag = a.getTag();
                l = new Lemma(lemma, tag, t);
            } else {
                l = new Lemma(t.form, "", null);
            }
            lemmas.add(l);
        }
        return lemmas;
    }

    /**
     * Class to obtain elemental tokens that will provide the morphological analysis
     * where the PoS are assigned
     */
    public class Token {
        private String type;
        private int id;
        private int inip;
        private int endp;
        private DaFont style;
        private String separation;
        @SerializedName("quote_level")
        private int quoteLevel;
        @SerializedName("affected_by_negation")
        private String affectedByNegation;
        @SerializedName("token_list")
        private List<Token> tokenList;
        private String form;
        @SerializedName("analysis_list")
        private List<Analysis> analysisList;
        private int head;
        @SerializedName("syntactic_tree_relation_list")
        private List<TreeRelation> syntacticTreeRelationList;
        @SerializedName("sense_list")
        private List<Sense> senseList;
        @SerializedName("topic_list")
        private Topic topicList; // despite its name, it is not a list.

        /**
         * Indicates which is the type of the token
         * @return Type of the token
         */
        public String getType() {
            return type;
        }

        /**
         * Identifier of the token unique for the request and represented by a natural number.
         * @return Identifier of the token
         */
        public int getId() {
            return id;
        }

        /**
         * Initial position of the token, starting from 0
         * @return Initial position of the token
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the token
         * @return End position of the token
         */
        public int getEndp() {
            return endp;
        }

        /**
         * This object contains information about the style of the text
         * @return The style of the text
         * @see DaFont
         */
        public DaFont getStyle() {
            return style;
        }

        /**
         * Describes how the token is separated with respect to the previous one
         * @return How the token is separated
         */
        public String getSeparation() {
            return separation;
        }

        /**
         * Index that indicates the level of quote of an element
         * @return The level of quote of an element
         */
        public int getQuoteLevel() {
            return quoteLevel;
        }

        /**
         * If a token is affected by a negation,
         * its sentiment may vary, and so,
         * this field will be added to the analysis
         * @return If a token is affected by a negation
         */
        public String getAffectedByNegation() {
            return affectedByNegation;
        }

        /**
         * List of children of a token
         * @return List of children of a token
         * @see Token
         */
        public List<Token> getTokenList() {
            return list(tokenList);
        }

        /**
         * Form of the token
         * @return Form of the token
         */
        public String getForm() {
            return form;
        }

        /**
         * List with all the possible morphosyntactic analyses of the token
         * @return List morphosyntactic analyses of the token
         * @see Analysis
         */
        public List<Analysis> getAnalysisList() {
            return list(analysisList);
        }

        /**
         * Identifies which child of the token defines its function by its token id
         * @return Identifies which child of the token defines
         */
        public int getHead() {
            return head;
        }

        /**
         * List of syntactic relations of the token
         * @return List of syntactic relations of the token
         * @see TreeRelation
         */
        public List<TreeRelation> getSyntacticTreeRelationList() {
            return list(syntacticTreeRelationList);
        }

        /**
         * List of senses or semantic analyses associated to the token
         * @return List of senses or semantic analyses
         * @see Sense
         */
        public List<Sense> getSenseList() {
            return list(senseList);
        }

        /**
         * This element will show any topics associated to the token
         * @return Topics associated to the token
         * @see Topic
         */
        public Topic getTopicList() {
            return topicList;
        }
    }

    /**
     * Class to obtain the style of the text
     */
    public class DaFont {
        private String isBold;
        private String isItalics;
        private String isUnderlined;
        private String isTitle;

        /**
         * It will have the value yes if the token in the input is in bold, no otherwise
         * @return A string it will have the value yes if the token in the input is in bold, no otherwise
         */
        public String getIsBold() {
            return isBold;
        }

        /**
         * It will have the value yes if the token in the input is in italics, no otherwise
         * @return A string it will have the value yes if the token in the input is in italics, no otherwise
         */
        public String getIsItalics() {
            return isItalics;
        }

        /**
         * It will have the value yes if the token in the input is underscored, no otherwise
         * @return A string it will have the value yes if the token in the input is underscored, no otherwise
         */
        public String getIsUnderlined() {
            return isUnderlined;
        }

        /**
         * It will have the value yes if the token in the input is in a title, no otherwise
         * @return A string it will have the value yes if the token in the input is in a title, no otherwise
         */
        public String getIsTitle() {
            return isTitle;
        }
    }

    /**
     * Class to obtain how we will represent each node of our morphosyntactic tree
     */
    public class Analysis {
        private String origin;
        private String tag;
        private String lemma;
        @SerializedName("original_form")
        private String originalForm;
        @SerializedName("tag_info")
        private String tagInfo;
        @SerializedName("sense_id_list")
        private List<SenseId> senseIdList;

        /**
         * Specifies where the analysis comes from,
         * specifically if any additional techniques have been applied to find the analysis
         * @return A string specifies where the analysis comes from
         */
        public String getOrigin() {
            return origin;
        }

        /**
         * Morphosyntactic tag associated to the token
         * @return A string specifies a tag associated to the token
         */
        public String getTag() {
            return tag;
        }

        /**
         * Lemma associated to the analysis
         * @return A string specifies a lemma associated to the analysis
         */
        public String getLemma() {
            return lemma;
        }

        /**
         * Original form of the token
         * @return A string specifies how it appears in the text
         */
        public String getOriginalForm() {
            return originalForm;
        }

        /**
         * Explained values of the morphosyntactic tag
         * @return A string explained values of the morphosyntactic tag
         */
        public String getTagInfo() {
            return tagInfo;
        }

        /**
         * List of semantic analyses associated to the morphosyntactic analysis
         * @return A list of sense identifiers
         * @see SenseId
         */
        public List<SenseId> getSenseIdList() {
            return list(senseIdList);
        }
    }

    /**
     * Class to obtain a syntactic relations of the token
     */
    public class TreeRelation {
        private int id;
        private String type;

        /**
         * Token id of the token to which it is related
         * @return A int of the token id
         */
        public int getId() {
            return id;
        }

        /**
         * Type of relation
         * @return A string specified type of relation
         */
        public String getType() {
            return type;
        }
    }

    /**
     * Class to obtain a sense identifiers
     */
    public class SenseId {
        @SerializedName("sense_id")
        private String senseId;

        /**
         * Sense identifiers
         * @return A string specified a sense identifiers
         */
        public String getSenseId() {
            return senseId;
        }
    }

    /**
     * Class to obtain a senses or semantic analyses associated to the token
     */
    public class Sense {
        private String id;
        private String form;
        private String info;

        /**
         * Identifier of the sense
         * @return A string specified an identifier of the sense
         */
        public String getId() {
            return id;
        }

        /**
         * Form associated to this sense in the language specified in the ilang parameter
         * @return A string specified the form associated to this sense
         */
        public String getForm() {
            return form;
        }

        /**
         * The attributes that conform the semantic information associated to the sense
         * @return A string with all the attributes that conform the semantic information
         */
        public String getInfo() {
            return info;
        }
    }

    /**
     * Class to obtain topics associated to the token
     */
    public class Topic {
        @SerializedName("entity_list")
        private List<Entity> entityList;
        @SerializedName("concept_list")
        private List<Concept> conceptList;
        @SerializedName("time_expression_list")
        private List<Time> timeExpressionList;
        @SerializedName("money_expression_list")
        private List<Money> moneyExpressionList;
        @SerializedName("quantity_expression_list")
        private List<Quantity> quantityExpressionList;
        @SerializedName("other_expression_list")
        private List<Other> otherExpressionList;
        @SerializedName("quotation_list")
        private List<Quotation> quotationList;
        @SerializedName("relation_list")
        private List<Relation> relationList;

        /**
         * Entities found in the input text
         * @return A list of named entities found in the text
         * @see Entity
         */
        public List<Entity> getEntityList() {
            return list(entityList);
        }

        /**
         * Concepts found in the input text
         * @return A list of named concepts found in the text
         * @see Concept
         */
        public List<Concept> getConceptList() {
            return list(conceptList);
        }

        /**
         * Time expressions found in the input text
         * @return A list of the time expressions found in the input text
         * @see Time
         */
        public List<Time> getTimeExpressionList() {
            return list(timeExpressionList);
        }

        /**
         * Money expressions found in the text
         * @return A list of money expressions found in the text
         * @see Money
         */
        public List<Money> getMoneyExpressionList() {
            return list(moneyExpressionList);
        }

        /**
         * Quantities found in the text
         * @return A list of quantities found in the text
         * @see Quantity
         */
        public List<Quantity> getQuantityExpressionList() {
            return list(quantityExpressionList);
        }

        /**
         * Unknown alphanumeric patterns found in the text
         * @return A list of unknown alphanumeric patterns found in the text
         * @see Other
         */
        public List<Other> getOtherExpressionList() {
            return list(otherExpressionList);
        }

        /**
         * Quotations found in a text
         * @return A list of quotations found in a text
         * @see Quotation
         */
        public List<Quotation> getQuotationList() {
            return list(quotationList);
        }

        /**
         * Syntactic triples (subject-action-object) found in the text
         * @return A list of syntactic triples (subject-action-object) found in the tex
         * @see Relation
         */
        public List<Relation> getRelationList() {
            return list(relationList);
        }

    }

    /**
     * Class to obtain a list of quotations found in a text
     * and represented by the quotation object
     */
    public class Quotation {
        private String form;
        @SerializedName("who")
        private Who quotationWho;
        private Verb verb;
        private int inip;
        private int endp;

        /**
         * Content of the quote as it appears in the text
         * @return A string content of the quote
         */
        public String getForm() {
            return form;
        }

        /**
         * Who the quote is attributed to
         * @return A class Who
         * @see Who
         */
        public Who getWho() {
            return quotationWho;
        }

        /**
         * Verb associated to the quotation
         * @return A class Verb
         * @see Verb
         */
        public Verb getVerb() {
            return verb;
        }

        /**
         * Initial position of the expression
         * @return int with initial position of the expression
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the expression
         * @return int with end position of the expression
         */
        public int getEndp() {
            return endp;
        }
    }

    /**
     * Class to obtain a form and lemma associated to the verb
     */
    public class Verb {
        private String form;
        private String lemma;

        /**
         * Field associated to the quotation
         * @return A string how it appears in the text
         */
        public String getForm() {
            return form;
        }

        /**
         * Field associated to the quotation
         * @return A string with lemma of the verb
         */
        public String getLemma() {
            return lemma;
        }
    }

    /**
     * Class to obtain a form and lemma associated to who the quote is attributed
     */
    public class Who {
        private String form;
        private String lemma;

        /**
         * Field associated to the quotation
         * @return A string how it appears in the text
         */
        public String getForm() {
            return form;
        }

        /**
         * Field associated to the quotation
         * @return A string with lemma to who the quote is attributed
         */
        public String getLemma() {
            return lemma;
        }
    }

    /**
     * Class to obtain some specific patterns will be considered known ones
     */
    public class Other {
        private String form;
        private String type;
        private int inip;
        private int endp;

        /**
         * Form of expression
         * @return A string form of expression
         */
        public String getForm() {
            return form;
        }

        /**
         * Type of expression
         * @return A string type of expression
         */
        public String getType() {
            return type;
        }

        /**
         * Initial position of the expression
         * @return int with initial position of the expression
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the expression
         * @return int with end position of the expression
         */
        public int getEndp() {
            return endp;
        }
    }

    /**
     * Class to obtain quantity expressions found in the text
     */
    public class Quantity {
        private String form;
        @SerializedName("amount_form")
        private String amountForm;
        @SerializedName("numeric_value")
        private int numericValue;
        private String unit;
        private int inip;
        private int endp;

        /**
         * Form of quantity expression
         * @return A string with form of quantity expression
         */
        public String getForm() {
            return form;
        }

        /**
         * Amount associated to the quantity expression as it appears in the text
         * @return A string amount associated to the quantity expression
         */
        public String getAmountForm() {
            return amountForm;
        }

        /**
         * Equivalent numeric value of the amount detected
         * @return int with numeric value of the amount detected
         */
        public int getNumericValue() {
            return numericValue;
        }

        /**
         * Standarized value of the unit detected for the expression
         * @return A string with value of the unit detected for the expression
         */
        public String getUnit() {
            return unit;
        }

        /**
         * Initial position of the quantity expression
         * @return int with initial position of the quantity expression
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the quantity expression
         * @return int with end position of the quantity expression
         */
        public int getEndp() {
            return endp;
        }
    }

    /**
     * Class to obtain money expression found in the text
     * and represented as money expression objects
     */
    public class Money {
        private String form;
        @SerializedName("amount_form")
        private String amountForm;
        @SerializedName("numeric_value")
        private int numericValue;
        private String currency;
        private int inip;
        private int endp;

        /**
         * Form of money expression
         * @return A string with form of money expression
         */
        public String getForm() {
            return form;
        }

        /**
         * Amount associated to the money expression as it appears in the text
         * @return A string with amount associated to the money expression
         */
        public String getAmountForm() {
            return amountForm;
        }

        /**
         * Equivalent numeric value of the amount of money
         * @return int with numeric value of the amount of money
         */
        public int getNumericValue() {
            return numericValue;
        }

        /**
         * ISO4217 value associated to the currency in the money expression
         * @return A string with ISO4217 value associated to the currency in the money expression
         */
        public String getCurrency() {
            return currency;
        }

        /**
         * Initial position of the money expression
         * @return int with initial position of the money expression
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the money expression
         * @return int with end position of the money expression
         */
        public int getEndp() {
            return endp;
        }
    }

    /**
     * Class to obtain time expressions found in the input text and represented as time expression objects
     */
    public class Time {
        private String form;
        @SerializedName("normalized_form")
        private String normalizedForm;
        @SerializedName("actual_time")
        private String actualTime;
        private String precision;
        private int inip;
        private int endp;

        /**
         * Form of the time expression
         * @return A string with the form of the time expression
         */
        public String getForm() {
            return form;
        }

        /**
         * Normalized form associated to the time expression
         * @return A string with normalized form associated to the time expression
         */
        public String getNormalizedForm() {
            return normalizedForm;
        }

        /**
         * Actual time relative to the given time reference, based on the normalized form
         * @return A string with actual time relative to the given time reference
         */
        public String getActualTime() {
            return actualTime;
        }

        /**
         * Level of precision for actual time
         * @return A string with level of precision for actual time
         */
        public String getPrecision() {
            return precision;
        }

        /**
         * Initial position of the time expression
         * @return int with initial position of the time expression
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the time expression
         * @return int with end position of the time expression
         */
        public int getEndp() {
            return endp;
        }
    }

    /**
     * Class to obtain concept found in the input text
     */
    public class Concept {
        private String form;
        private String id;
        private Sementity sementity;
        @SerializedName("semtheme_list")
        private List<Semtheme> semthemeList;

        /**
         * Form of the concept in the language specified in the ilang parameter
         * @return A string with form of the concept
         */
        public String getForm() {
            return form;
        }

        /**
         * Alphanumeric string that univocally identifies the concept
         * @return A string with alphanumeric string that univocally identifies the concept
         */
        public String getId() {
            return id;
        }

        /**
         * Type of concept
         * @return A Sementity class
         * @see Sementity
         */
        public Sementity getSementity() {
            return sementity;
        }

        /**
         * List of thematic classifications
         * @return A list of thematic classifications
         * @see Semtheme
         */
        public List<Semtheme> getSemthemeList() {
            return list(semthemeList);
        }
    }

    /**
     * Class to obtain entity found in the input text
     */
    public class Entity {
        private String form;
        private String id;
        private Sementity sementity;
        @SerializedName("semtheme_list")
        private List<Semtheme> semthemeList;

        /**
         * Form of the entity in the language specified in the ilang parameter
         * @return A string with form of the entity
         */
        public String getForm() {
            return form;
        }

        /**
         * Alphanumeric string that univocally identifies the entity
         * @return A string with alphanumeric string
         */
        public String getId() {
            return id;
        }

        /**
         * Type of entity
         * @return A sementity class
         * @see Sementity
         */
        public Sementity getSementity() {
            return sementity;
        }

        /**
         * List of thematic classifications
         * @return A list of thematic classifications
         * @see Semtheme
         */
        public List<Semtheme> getSemthemeList() {
            return list(semthemeList);
        }
    }

    /**
     * Class to obtain a list of thematic classifications
     */
    public class Semtheme {
        private String id;
        private String type;

        /**
         * Identifier of the node associated to the theme the entity belongs to
         * @return A string with identifier of the node associated to the theme the entity belongs to
         */
        public String getId() {
            return id;
        }

        /**
         * Provides a more user-friendly name of all the levels of the theme classification hierarchy
         * @return A string provides a mor user-friendly name of all the levels of the theme classification hierarchy
         */
        public String getType() {
            return type;
        }
    }

    /**
     * Class to obtain type of entity/concept
     */
    public class Sementity {
        @SerializedName("class")
        private String sementityClass;
        private String fiction;
        private String id;
        private String type;

        /**
         * Indicates if the node is a class or an instance in the ontology
         * @return A string indicates if the node is a class or an instance
         */
        public String getSementityClass() {
            return sementityClass;
        }

        /**
         * Indicates if the element is a fictional or non-fictional
         * @return A string indicates fiction or nonfiction
         */
        public String getFiction() {
            return fiction;
        }

        /**
         * Identifier of the node associated to the entity type
         * @return A string indicates a identifier of the node associated to the entity type
         */
        public String getId() {
            return id;
        }

        /**
         * Provides a more user-friendly notation for the type classification hierarchy of the entity
         * @return A string provides a mor user-friendly notation for the type classification hierarchy of the entity
         */
        public String getType() {
            return type;
        }
    }

    /**
     * Class to obtain relation list
     */
    public class Relation {
        private String form;
        private int inip;
        private int endp;
        private Subject subject;
        private Verb verb;
        @SerializedName("complement_list")
        private List<Complement> complementList;
        private int degree;

        /**
         * Sentence in which the relation appears
         * @return A string indicates in which the relation appears
         */
        public String getForm() {
            return form;
        }

        /**
         * Initial position of the sentence the relation appears in
         * @return int indicate initial position of the sentence the relation appears in
         */
        public int getInip() {
            return inip;
        }

        /**
         * End position of the sentence the relation appears in
         * @return int indicate initial position of the sentence the relation appears in
         */
        public int getEndp() {
            return endp;
        }

        /**
         * Subject of the relation
         * @return A subject class
         * @see Subject
         */
        public Subject getSubject() {
            return subject;
        }

        /**
         * Verb of the relation
         * @return A verb class
         * @see Verb
         */
        public Verb getVerb() {
            return verb;
        }

        /**
         * List of complements of the verb represented by the complements objects
         * @return A list of complements of the verb
         */
        public List<Complement> getComplementList() {
            return list(complementList);
        }

        /**
         * Degree of proximity of the relation
         * @return int indicate degree of proximity of the relation
         */
        public int getDegree() {
            return degree;
        }
    }

    /**
     * Class to obtain subject of the relation
     */
    public class Subject {
        private String form;
        @SerializedName("lemma_list")
        private String [] lemmaList;
        @SerializedName("sense_id_list")
        private String [] senseIdList;

        /**
         * How it appears in the text
         * @return A string indicates how it appears in the text
         */
        public String getForm() {
            return form;
        }

        /**
         * List of lemmas of the element
         * @return array with lemmas of the element
         */
        public String[] getLemmaList() {
            return lemmaList;
        }

        /**
         * Ids associated to the concept or entity the subject refers to
         * @return array with ids associated to the concept or entity
         */
        public String[] getSenseIdList() {
            return senseIdList;
        }
    }

    /**
     * Class to obtain a list of complements of the verb represented by the complements objects
     */
    public class Complement {
        private String form;
        private String type;

        /**
         * How it appears in the text
         * @return A string indicates how it appears in the text
         */
        public String getForm() {
            return form;
        }

        /**
         * Type of complement
         * @return A string indicates type of complement
         */
        public String getType() {
            return type;
        }
    }

    @SerializedName("token_list")
    private List<Token> tokenList;

    /**
     * List to obtain elemental tokens that will provide the morphological analysis
     * @return A list elemental tokens that will provide the morphological analysis
     */
    public List<Token> getTokenList() {
        return list(tokenList);
    }

    /**
     * ParserResponse factory method
     * @param response The JSON response from MeaningCloud Parser API
     * @return ParserResponse
     */
    public static ParserResponse from(String response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ParserResponse r = gson.fromJson(response, ParserResponse.class);
        return r;
    }

    /**
     * Class constructor
     */
    private ParserResponse() {}

}
