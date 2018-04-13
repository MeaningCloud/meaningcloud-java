package com.meaningcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Sentiment Analysis request
 * You can find more information at https://www.meaningcloud.com/developer/sentiment-analysis/doc/2.1/request
 */
public class SentimentRequest extends Request {

    public static final Endpoint.Service DEFAULT_SERVICE = Endpoint.Service.SENTIMENT;
    public static final Endpoint DEFAULT_ENDPOINT = new Endpoint(DEFAULT_SERVICE);
    public static final TextFormat DEFAULT_TXT_F = TextFormat.PLAIN;
    public static final String DEFAULT_MODEL = "general_en";
    public static final boolean DEFAULT_EGP = true;
    public static final RelaxedTypography DEFAULT_RT = RelaxedTypography.DISABLED;
    public static final boolean DEFAULT_UW = false;
    public static final DisambiguationLevel DEFAULT_DM = DisambiguationLevel.SEMANTIC;
    public static final SemanticDisambiguationGrouping DEFAULT_SDG = SemanticDisambiguationGrouping.BY_TYPE_SMALLEST_LOCATION;
    public static final String DEFAULT_UD = "";
    public static final boolean DEFAULT_VERBOSE = false;
    public static final Payload DEFAULT_PAYLOAD = new NoPayload();

    /**
     * Interface to obtain parameters
     */
    interface Payload {
        Map<String, String> getParams();
    }

    /**
     * Class to add text
     */
    class TextPayload implements Payload {
        public final String txt;

        /**
         * Constructor
         * @param txt Input text that's going to be classified
         */
        public TextPayload(String txt) {
            this.txt = txt;
        }

        /**
         * Obtain parameters
         * @return A HasMap with text
         */
        public Map<String, String> getParams() {
            Map<String, String> map = new HashMap<>();
            map.put("txt", txt);
            return map;
        }
    }

    /**
     * Class to add file
     */
    class FilePayload implements Payload {
        public final File file;
        public final String contents;

        /**
         * Constructor
         * @param file Input file with the content to analyze
         * @throws IOException Raised when a parameter value can't be accepted
         */
        public FilePayload(File file) throws IOException {
            this.file = file;
            InputStream is = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead = 1;
            StringBuilder sb = new StringBuilder();
            while(bytesRead > 0) {
                bytesRead = is.read(buffer);
                if (bytesRead > 0) {
                    sb.append(new String(buffer, 0, bytesRead));
                }
            }
            is.close();
            this.contents = sb.toString();
        }

        /**
         * Obtain parameters
         * @return A HasMap with the contents
         */
        public Map<String, String> getParams() {
            Map<String, String> map = new HashMap<>();
            map.put("txt", contents);
            return map;
        }
    }

    /**
     * Class to add URL
     */
    class URLPayload implements Payload {
        public final String url;

        /**
         * Constructor
         * @param url URL with the content to classify
         */
        public URLPayload(String url) {
            this.url = url;
        }

        /**
         * Obtain parameters
         * @return A HasMap with the URL
         */
        public Map<String, String> getParams() {
            Map<String, String> map = new HashMap<>();
            map.put("url", url);
            return map;
        }
    }

    /**
     * Class to obtain empty parameters
     */
    static class NoPayload implements Payload {

        /**
         * Obtain parameters
         * @return RuntimeException
         */
        public Map<String, String> getParams() {
            throw new RuntimeException ("No payload defined");
        }
    }

    public final Language lang;
    public final TextFormat txtf;
    public final String model;
    public final boolean egp;
    public final RelaxedTypography rt;
    public final boolean uw;
    public final DisambiguationLevel dm;
    public final SemanticDisambiguationGrouping sdg;
    public final String ud;
    public final boolean verbose;
    public final Payload payload;

    /**
     * Sends the request to a specific endpoint
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public SentimentResponse send(Endpoint endpoint) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("src", "mc-java");
        params.put("lang", lang.code());
        params.put("txtf", txtf.code());
        params.put("model", model);
        params.put("egp", egp ? "y" : "n");
        params.put("rt", rt.code());
        params.put("uw", uw ? "y" : "n");
        params.put("dm", dm.code());
        params.put("sdg", sdg.code());
        params.put("ud", ud);
        params.put("verbose", verbose ? "y" : "n");

        for (Map.Entry<String, String> x : payload.getParams().entrySet()) {
            params.put(x.getKey(), x.getValue());
        }

        String response = post(endpoint, params);
        return SentimentResponse.from(response);
    }

    /**
     * Sends the request to the default endpoint <tt>api.meaningcloud.com</tt>
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public SentimentResponse send() throws IOException {
        return send(DEFAULT_ENDPOINT);
    }

    /**
     * Sends the request to a specific server
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public SentimentResponse send(Endpoint.Server server) throws IOException {
        return send(server.with(DEFAULT_SERVICE));
    }

    /**
     * SentimentRequest constructor
     * @param key User's API key
     * @param lang The text language
     * @param txtf The text format parameter specifies if the text included in the txt parameter uses markup language
     * @param model Sentiment model chosen
     * @param egp Expand global polarity
     * @param rt This parameter indicates how reliable the text to analyze is
     * @param uw Deal with unknown words
     * @param dm Type of disambiguation applied
     * @param sdg Semantic disambiguation grouping
     * @param ud The user dictionary allows to include user-defined entities and concepts in the sentiment analysis
     * @param verbose it shows additional information about the sentiment analysis specifically
     * @param payload Interface to obtain parameters
     * @throws Request.ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest(String key,
                            Language lang,
                            TextFormat txtf,
                            String model,
                            boolean egp,
                            RelaxedTypography rt,
                            boolean uw,
                            DisambiguationLevel dm,
                            SemanticDisambiguationGrouping sdg,
                            String ud,
                            boolean verbose,
                            Payload payload) throws Request.ParameterValidationException {
        super(key);
        this.lang = lang;
        this.txtf = txtf;
        this.model = model;
        this.egp = egp;
        this.rt = rt;
        this.uw = uw;
        this.dm = dm;
        this.sdg = sdg;
        this.ud = ud;
        this.verbose = verbose;
        this.payload = payload;
    }

    /**
     * Builds a sentiment request with the given API key and language
     * @param key User's API key
     * @param lang The text language
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public static SentimentRequest build(String key, Language lang) throws ParameterValidationException {
        return new SentimentRequest(key, lang, DEFAULT_TXT_F, DEFAULT_MODEL, DEFAULT_EGP, DEFAULT_RT, DEFAULT_UW, DEFAULT_DM, DEFAULT_SDG, DEFAULT_UD, DEFAULT_VERBOSE, DEFAULT_PAYLOAD);
    }

    /**
     * Builds a sentiment request with the text
     * @param txt Text to use for a sentiment API calls
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withText(String txt) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, new SentimentRequest.TextPayload(txt));
    }

    /**
     * Builds a sentiment request with file
     * @param file File with the content to analyze
     * @return A sentiment request object
     * @throws IOException Raised when a parameter value can't be accepted
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withFile(File file) throws IOException, ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, new SentimentRequest.FilePayload(file));
    }

    /**
     * Builds a sentiment request with url
     * @param url Url of the content to analyze
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withURL(URL url) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, new SentimentRequest.URLPayload(url.toString()));
    }

    /**
     * Builds a sentiment request with text format
     * @param txtf The text format parameter specifies if the text included in the txt parameter uses markup language
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withTxtf(TextFormat txtf) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }

    /**
     * Builds a sentiment request with the model
     * @param model Sentiment model chosen
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withModel(String model) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }

    /**
     * Builds a sentiment request with the expand global polarity
     * @param egp Expand global polarity
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withPolarity(boolean egp) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }

    /**
     * Builds a sentiment request with the relaxed typography
     * @param rt Relaxed typography
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withRT(RelaxedTypography rt) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }

    /**
     * Builds a sentiment request with the unknown words
     * @param uw Unknown words
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withUW(boolean uw) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }

    /**
     * Builds a sentiment request with the disambiguation level
     * @param dm Type of disambiguation applied
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withDM(DisambiguationLevel dm) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }

    /**
     * Bulds a sentiment request with the semantic disambiguation grouping
     * @param sdg Semantic disambiguation grouping
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withSDG(SemanticDisambiguationGrouping sdg) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }

    /**
     * Builds a sentiment request with the user dictionary
     * @param ud User dictionary
     * @return A sentiment request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public SentimentRequest withUD(String ud) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }

    /**
     * Builds a sentiment request with the verbose
     * @param verbose it shows additional information about the sentiment analysis specifically
     * @return A sentiment request object
     * @throws ParameterValidationException it shows additional information about the sentiment analysis specifically
     */
    public SentimentRequest withVerbose(boolean verbose) throws ParameterValidationException {
        return new SentimentRequest(key, lang, txtf, model, egp, rt, uw, dm, sdg, ud, verbose, payload);
    }
}
