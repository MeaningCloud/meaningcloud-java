package com.meaningcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Deep Categorization request
 * You can find more information at https://www.meaningcloud.com/developer/deep-categorization/doc/1.0/request
 */
public class DeepCatRequest extends Request {

    public static final Endpoint.Service DEFAULT_SERVICE = Endpoint.Service.DEEP_CATEGORIZATION;
    public static final Endpoint DEFAULT_ENDPOINT = new Endpoint(DEFAULT_SERVICE);
    public static final boolean DEFAULT_VERBOSE = false;
    public static final boolean DEFAULT_POLARITY = false;
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
            while(bytesRead > 0){
                bytesRead = is.read(buffer);
                if (bytesRead > 0){
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

    public final String model;
    public final boolean verbose;
    public final boolean polarity;
    public final Payload payload;

    /**
     * Sends the request to a specific endpoint
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public DeepCatResponse send(Endpoint endpoint) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("src", "mc-java");
        params.put("model", model);
        params.put("verbose", verbose ? "y" : "n");
        params.put("polarity", polarity ? "y" : "n");

        for (Map.Entry<String, String> x : payload.getParams().entrySet()) {
            params.put(x.getKey(), x.getValue());
        }

        String response = post(endpoint, params);
        return DeepCatResponse.from(response);
    }

    /**
     * Sends the request to the default endpoint <tt>api.meaningcloud.com</tt>
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public DeepCatResponse send() throws IOException {
        return send(DEFAULT_ENDPOINT);
    }

    /**
     * Sends the request to a specific server
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public DeepCatResponse send(Endpoint.Server server) throws IOException {
        return send(server.with(DEFAULT_SERVICE));
    }

    /**
     * DeepCatRequest constructor
     * @param key User's API key
     * @param model Categorization model to use
     * @param verbose Additional information about the process
     * @param polarity Determines if categories will contain an associated polarity value
     * @param payload Interface to obtain parameters
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public DeepCatRequest (String key,
                          String model,
                          boolean verbose,
                          boolean polarity,
                          Payload payload) throws ParameterValidationException {
        super(key);
        this.model = model;
        this.verbose = verbose;
        this.polarity = polarity;
        this.payload = payload;
    }

    /**
     * Builds a deep categorization request with the given API key and model
     * @param key User's API key
     * @param model Categorization model to use
     * @return A deep categorization request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public static DeepCatRequest build(String key, String model) throws ParameterValidationException {
        return new DeepCatRequest(key, model, DEFAULT_VERBOSE, DEFAULT_POLARITY, DEFAULT_PAYLOAD);
    }

    /**
     * Builds a deep categorization request with text
     * @param txt Input text
     * @return A deep categorization request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public DeepCatRequest withText(String txt) throws ParameterValidationException {
        return new DeepCatRequest(key, model, verbose, polarity, new TextPayload(txt));
    }

    /**
     * Builds a deep categorization request with file
     * @param file Input file with the content to analyze
     * @return A deep categorization request object
     * @throws IOException Raised when a parameter value can't be accepted
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public DeepCatRequest withFile(File file) throws IOException, ParameterValidationException {
        return new DeepCatRequest(key, model, verbose, polarity, new FilePayload(file));
    }

    /**
     * Builds a deep categorization request with url
     * @param url URL of the content to analyze
     * @return A deep categorization request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public DeepCatRequest withURL(URL url) throws ParameterValidationException {
        return new DeepCatRequest(key, model, verbose, polarity, new URLPayload(url.toString()));
    }

    /**
     * Builds a deep categorization request with verbose
     * @param verbose Additional information about the process
     * @return A deep categorization request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public DeepCatRequest withVerbose(boolean verbose) throws ParameterValidationException {
        return new DeepCatRequest(key, model, verbose, polarity, payload);
    }

    /**
     * Builds a deep categorization request with polarity
     * @param polarity Determines if categories will contain an associated polarity value
     * @return A deep categorization request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public DeepCatRequest withPolarity(boolean polarity) throws ParameterValidationException {
        return new DeepCatRequest(key, model, verbose, polarity, payload);
    }
}
