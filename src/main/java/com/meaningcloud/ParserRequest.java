package com.meaningcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents Lemmatization, PoS and Parsing requests
 * You can find more information at https://www.meaningcloud.com/developer/lemmatization-pos-parsing/doc/2.0/request
 */
public class ParserRequest extends Request {

    public static final Endpoint.Service DEFAULT_SERVICE = Endpoint.Service.PARSER;
    public static final Endpoint DEFAULT_ENDPOINT = new Endpoint(DEFAULT_SERVICE);
    public static final ILanguage DEFAULT_ILANG = ILanguage.EN;
    public static final TopicType DEFAULT_TOPICS_TO_DETECT = TopicType.ALL;
    public static final boolean DEFAULT_VERBOSE = false;
    public static final TextFormat DEFAULT_TXTF = TextFormat.PLAIN;
    public static final RelaxedTypography DEFAULT_RT = RelaxedTypography.DISABLED;
    public static final boolean DEFAULT_UW = false;
    public static final DisambiguationLevel DEFAULT_DM = DisambiguationLevel.SEMANTIC;
    public static final SemanticDisambiguationGrouping DEFAULT_SDG = SemanticDisambiguationGrouping.BY_TYPE_SMALLEST_LOCATION;
    public static final String DEFAULT_CONT = "";
    public static final String DEFAULT_SM = "";
    public static final String DEFAULT_ST = "n";
    public static final String DEFAULT_TIMEREF = "";
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
    class TextPayload implements ParserRequest.Payload {
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
     * Class to obtain empty parameters
     */
    static class NoPayload implements Payload {

        /**
         * Obtain parameters
         * @return RuntimeException
         */
        public Map<String, String> getParams() {
            throw new RuntimeException("No payload defined");
        }
    }

    public final Language lang;
    public final ILanguage ilang;
    public final TopicType topicToDetect;
    public final boolean verbose;
    public final TextFormat txtf;
    public final RelaxedTypography rt;
    public final boolean uw;
    public final DisambiguationLevel dm;
    public final SemanticDisambiguationGrouping sdg;
    public final String cont;
    public final String sm;
    public final String st;
    public final String timeref;
    public final Payload payload;

    /**
     * Sends the request to a specific endpoint
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public ParserResponse send(Endpoint endpoint) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("src", "mc-java");
        params.put("lang", lang.code());
        params.put("ilang", ilang.code());
        params.put("tt", topicToDetect.code());
        params.put("verbose", verbose ? "y" : "n");
        params.put("txtf", txtf.code());
        params.put("rt", rt.code());
        params.put("uw", uw ? "y" : "n");
        params.put("dm", dm.code());
        params.put("sdg", sdg.code());
        params.put("cont", cont);
        params.put("sm", sm);
        params.put("st", st);
        params.put("timeref", timeref);

        for (Map.Entry<String, String> x : payload.getParams().entrySet()) {
            params.put(x.getKey(), x.getValue());
        }

        String response = transport.send(endpoint, params);
        return ParserResponse.from(response);
    }

    /**
     * Sends the request to the default endpoint <tt>api.meaningcloud.com</tt>
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public ParserResponse send() throws IOException {
        return send(DEFAULT_ENDPOINT);
    }

    /**
     * Sends the request to a specific server
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public ParserResponse send(Endpoint.Server server) throws IOException {
        return send(server.with(DEFAULT_SERVICE));
    }

    /**
     * ParserRequest constructor
     * @param key User's API key
     * @param lang The text language
     * @param ilang The language in which the values returned will appear
     * @param topicToDetect Topic types to extract
     * @param verbose It shows additional information about the morphosyntactic tagsets and sentiment analysis
     * @param txtf The text format parameter specifies if the text included in the txt parameter uses markup language
     * @param rt Deal with relaxed typography
     * @param uw Deal with unknown words
     * @param dm Type of disambiguation applied
     * @param sdg Semantic disambiguation grouping
     * @param cont Disambiguation context
     * @param sm Sentiment model chosen
     * @param st Show subtopics
     * @param timeref This value allows to set a specific time reference to detect the actual value of all the relative time expressions detected in the text
     * @param payload Interface to obtain parameters
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ParserRequest(Transport transport,
                         String key,
                         Language lang,
                         ILanguage ilang,
                         TopicType topicToDetect,
                         boolean verbose,
                         TextFormat txtf,
                         RelaxedTypography rt,
                         boolean uw,
                         DisambiguationLevel dm,
                         SemanticDisambiguationGrouping sdg,
                         String cont,
                         String sm,
                         String st,
                         String timeref,
                         Payload payload) throws ParameterValidationException {
        super(transport, key);
        this.lang = lang;
        this.ilang = ilang;
        this.topicToDetect = topicToDetect;
        this.verbose = verbose;
        this.txtf = txtf;
        this.rt = rt;
        this.uw = uw;
        this.dm = dm;
        this.sdg = sdg;
        this.cont = cont;
        this.sm = sm;
        this.st = st;
        this.timeref = timeref;
        this.payload = payload;
    }

    /**
     * Builds a parser request with the given API key and language
     * @param key User's API key
     * @param lang The text language
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     * @see Request.Language
     */
    public static ParserRequest build(String key, Language lang) throws ParameterValidationException {
        return new ParserRequest(DEFAULT_TRANSPORT, key, lang, DEFAULT_ILANG, DEFAULT_TOPICS_TO_DETECT, DEFAULT_VERBOSE, DEFAULT_TXTF, DEFAULT_RT, DEFAULT_UW, DEFAULT_DM, DEFAULT_SDG, DEFAULT_CONT, DEFAULT_SM, DEFAULT_ST, DEFAULT_TIMEREF, DEFAULT_PAYLOAD);
    }

    /**
     * Builds a parser request with the given API key and language
     * @param key User's API key
     * @param lang The text language
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     * @see Request.Language
     */
    public static ParserRequest build(Transport transport, String key, Language lang) throws ParameterValidationException {
        return new ParserRequest(transport, key, lang, DEFAULT_ILANG, DEFAULT_TOPICS_TO_DETECT, DEFAULT_VERBOSE, DEFAULT_TXTF, DEFAULT_RT, DEFAULT_UW, DEFAULT_DM, DEFAULT_SDG, DEFAULT_CONT, DEFAULT_SM, DEFAULT_ST, DEFAULT_TIMEREF, DEFAULT_PAYLOAD);
    }

    /**
     * Builds a parser request with the text
     * @param txt Text to use for parser API calls
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ParserRequest withText(String txt) throws ParameterValidationException {
        return new ParserRequest(transport, key, lang, ilang, topicToDetect, verbose, txtf, rt, uw, dm, sdg, cont, sm, st, timeref, new TextPayload(txt));
    }

    /**
     * Builds a parser request with the interface language
     * @param ilang It specifies the language in which the values returned will appear
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     * @see Request.Language
     */
    public ParserRequest withInterfaceLanguage(ILanguage ilang) throws ParameterValidationException {
        return new ParserRequest(transport, key, lang, ilang, topicToDetect, verbose, txtf, rt, uw, dm, sdg, cont, sm, st, timeref, payload);
    }

    /**
     * Builds a parser request with the topics to detect
     * @param ttd Topic types to extract
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     * @see Request.TopicType
     */
    public ParserRequest withTopicsToDetect(TopicType ttd) throws ParameterValidationException {
        return new ParserRequest(transport, key, lang, ilang, ttd, verbose, txtf, rt, uw, dm, sdg, cont, sm, st, timeref, payload);
    }

    /**
     * Builds a parser request with the verbose
     * @param verbose It shows additional information about the morphosyntactic tagsets and sentiment analysis
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ParserRequest withVerbose(boolean verbose) throws ParameterValidationException {
        return new ParserRequest(transport, key, lang, ilang, topicToDetect, verbose, txtf, rt, uw, dm, sdg, cont, sm, st, timeref, payload);
    }

    /**
     * Builds a parser request with text format
     * @param txtf The text format parameter specifies if the text included in the txt parameter uses markup language
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     * @see Request.TextFormat
     */
    public ParserRequest withTextFormat(TextFormat txtf) throws ParameterValidationException {
        return new ParserRequest(transport, key, lang, ilang, topicToDetect, verbose, txtf, rt, uw, dm, sdg, cont, sm, st, timeref, payload);
    }

    /**
     * Builds a parser request with time reference
     * @param timeref This value allows to set a specific time reference to detect the actual value of all the relative time expressions detected in the text. Values: YYYY-MM-DD hh:mm:ss GMT±HH:MM
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ParserRequest withTimeRef(String timeref) throws ParameterValidationException {
        return new ParserRequest(transport, key, lang, ilang, topicToDetect, verbose, txtf, rt, uw, dm, sdg, cont, sm, st, timeref, payload);
    }

    /**
     * Builds a parser request with file
     * @param file File with the content to analyze
     * @return A parser request object
     * @throws IOException If the parameters passed are incorrect
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ParserRequest withFile(File file) throws IOException, ParameterValidationException {
        return new ParserRequest(transport, key, lang, ilang, topicToDetect, verbose, txtf, rt, uw, dm, sdg, cont, sm, st, timeref, new FilePayload(file));
    }

    /**
     * Builds a parser request with url
     * @param url Url of the content to analyze
     * @return A parser request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ParserRequest withURL(URL url) throws ParameterValidationException {
        return new ParserRequest(transport, key, lang, ilang, topicToDetect, verbose, txtf, rt, uw, dm, sdg, cont, sm, st, timeref, new URLPayload(url.toString()));
    }
}
