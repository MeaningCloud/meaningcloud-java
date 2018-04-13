package com.meaningcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Represents Topics Extraction request
 * You can find more information at https://www.meaningcloud.com/developer/topics-extraction/doc/2.0/request
 */
public class TopicsRequest extends Request {

    public static final Endpoint.Service DEFAULT_SERVICE = Endpoint.Service.TOPICS;
    public static final Endpoint DEFAULT_ENDPOINT = new Endpoint(DEFAULT_SERVICE);
    public static final Language DEFAULT_INTERFACE_LANG = Language.EN;
    public static final TopicType DEFAULT_TOPICS_TO_DETECT = TopicType.ALL;
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
    public final Language interfaceLang;
    public final TopicType topicsToDetect;
    public final Payload payload;

    /**
     * Sends the request to a specific endpoint
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public TopicsResponse send(Endpoint endpoint) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("src", "mc-java");
        params.put("lang", lang.code());
        params.put("ilang", interfaceLang.code());
        params.put("tt", topicsToDetect.code());

        for (Map.Entry<String, String> x : payload.getParams().entrySet()) {
            params.put(x.getKey(), x.getValue());
        }

        String response = post(endpoint, params);
        return TopicsResponse.from(response);
    }

    /**
     * Sends the request to the default endpoint <tt>api.meaningcloud.com</tt>
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public TopicsResponse send() throws IOException {
        return send(DEFAULT_ENDPOINT);
    }

    /**
     * Sends the request to a specific server
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public TopicsResponse send(Endpoint.Server server) throws IOException {
        return send(server.with(DEFAULT_SERVICE));
    }

    /**
     * TopicsRequest constructor
     * @param key User's API key
     * @param lang The text lang
     * @param interfaceLang The language in which the values returned will appear
     * @param topicsToDetect Topic types to extract
     * @param payload Interface to obtain parameters
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    private TopicsRequest(String key,
                          Language lang,
                          Language interfaceLang,
                          TopicType topicsToDetect,
                          Payload payload) throws ParameterValidationException {
        super(key);
        this.lang = lang;
        this.interfaceLang = interfaceLang;
        this.topicsToDetect = topicsToDetect;
        this.payload = payload;
    }

    /**
     * Builds a topics request with the given API key and language
     * @param key User's API key
     * @param lang The test language
     * @return A topics request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public static TopicsRequest build(String key, Language lang) throws ParameterValidationException {
        return new TopicsRequest(key, lang, DEFAULT_INTERFACE_LANG, DEFAULT_TOPICS_TO_DETECT, DEFAULT_PAYLOAD);
    }

    /**
     * Builds a topics request with the interface language
     * @param ilang It specifies the language in which the values returned will appear
     * @return A topics request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public TopicsRequest withInterfaceLanguage(Language ilang) throws ParameterValidationException {
        return new TopicsRequest(key, lang, ilang, topicsToDetect, payload);
    }

    /**
     * Builds a topics request with the topics to detect
     * @param ttd Topic types to extract
     * @return A topics request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public TopicsRequest withTopicsToDetect(TopicType ttd) throws ParameterValidationException {
        return new TopicsRequest(key, lang, interfaceLang, ttd, payload);
    }


    /**
     * Builds a topics request with the text
     * @param txt Text to use for topics API calls
     * @return A Topics request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public TopicsRequest withText(String txt) throws ParameterValidationException {
        return new TopicsRequest(key, lang, interfaceLang, topicsToDetect, new TextPayload(txt));
    }

    /**
     * Builds a topics request with url
     * @param url Url of the content to analyze
     * @return A topics request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public TopicsRequest withURL(URL url) throws ParameterValidationException {
        return new TopicsRequest(key, lang, interfaceLang, topicsToDetect, new URLPayload(url.toString()));
    }

    /**
     * Builds a topics request with file
     * @param file File with the content to analyze
     * @return A topics request object
     * @throws IOException Raised when a parameter value can't be accepted
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public TopicsRequest withFile(File file) throws IOException, ParameterValidationException {
        return new TopicsRequest(key, lang, interfaceLang, topicsToDetect, new FilePayload(file));
    }
}
