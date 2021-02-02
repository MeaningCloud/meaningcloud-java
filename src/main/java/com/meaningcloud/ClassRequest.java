package com.meaningcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Represents a Text Classification request
 * You can find more information at https://www.meaningcloud.com/developer/text-classification/doc/2.0/request
 */

public class ClassRequest extends Request{

    public static final Endpoint.Service DEFAULT_SERVICE = Endpoint.Service.CLASS;
    public static final Endpoint DEFAULT_ENDPOINT = new Endpoint(DEFAULT_SERVICE);
    public static final boolean DEFAULT_VERBOSE = false;
    public static final String DEFAULT_CATEGORIES_FILTER = "";
    public static final Payload DEFAULT_PAYLOAD = new NoPayload();
    public static final HierarchyExpansion DEFAULT_HIERARCHY_EXPANSION = HierarchyExpansion.NO_EXPANSION;

    /**
     * Allowed values for Hierarchy Expansion
     */
    public static enum HierarchyExpansion {
        NO_EXPANSION("n"),
        ONLY_PARENTS("p"),
        ALL_ANCESTORS("a");

        private String expansion;

        HierarchyExpansion(String expansion) {
            this.expansion = expansion;
        }

        public String getValue() {
            return expansion;
        }
    }

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
        public Map<String, String> getParams(){
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

    public final String model;
    public final boolean verbose;
    public final String categoriesFilter;
    public final HierarchyExpansion expandHierarchy;
    public final Payload payload;

    /**
     * Sends the request to a specific endpoint
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public ClassResponse send(Endpoint endpoint) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("src", "mc-java");
        params.put("model", model);
        params.put("verbose", verbose ? "y" : "n");
        params.put("categories_filter", categoriesFilter);
        params.put("expand_hierarchy", expandHierarchy.getValue());

        for (Map.Entry<String, String> x : payload.getParams().entrySet()) {
            params.put(x.getKey(), x.getValue());
        }

        String response = transport.send(endpoint, params);
        return ClassResponse.from(response);
    }

    /**
     * Sends the request to the default endpoint <tt>api.meaningcloud.com</tt>
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public ClassResponse send() throws IOException {
        return send(DEFAULT_ENDPOINT);
    }

    /**
     * Sends the request to a specific server
     * @return The server response
     * @throws IOException Raised when the request couldn't be sent
     */
    public ClassResponse send(Endpoint.Server server) throws IOException {
        return send(server.with(DEFAULT_SERVICE));
    }

    /**
     * ClassRequest constructor
     * @param key User's API key
     * @param model Classification model to use
     * @param verbose It shows additional information about the classification
     * @param categoriesFilter List of prefixes of the code of the categories to which the classification is limited
     * @param expandHierarchy Hierarchy Expansion strategy.
     * @param payload Interface to obtain parameters
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    private ClassRequest (Transport transport,
                          String key,
                          String model,
                          boolean verbose,
                          String categoriesFilter,
                          HierarchyExpansion expandHierarchy,
                          Payload payload) throws ParameterValidationException {
        super(transport, key);
        this.model = model;
        this.verbose = verbose;
        this.categoriesFilter = categoriesFilter;
        this.expandHierarchy = expandHierarchy;
        this.payload = payload;
    }

    /**
     * Builds a class request with the given API key and model
     * @param key User's API key
     * @param model The classification model
     * @return A class request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public static ClassRequest build(String key, String model) throws ParameterValidationException {
        return new ClassRequest(DEFAULT_TRANSPORT, key, model, DEFAULT_VERBOSE, DEFAULT_CATEGORIES_FILTER, DEFAULT_HIERARCHY_EXPANSION, DEFAULT_PAYLOAD);
    }

    /**
     * Builds a class request with the given API key and model
     * @param key User's API key
     * @param model The classification model
     * @return A class request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public static ClassRequest build(Transport transport, String key, String model) throws ParameterValidationException {
        return new ClassRequest(transport, key, model, DEFAULT_VERBOSE, DEFAULT_CATEGORIES_FILTER, DEFAULT_HIERARCHY_EXPANSION, DEFAULT_PAYLOAD);
    }

    /**
     * Builds a class request with the text
     * @param txt Text to use for classification API calls
     * @return A class request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ClassRequest withText(String txt) throws ParameterValidationException {
        return new ClassRequest(transport, key, model, verbose, categoriesFilter, expandHierarchy, new TextPayload(txt));
    }

    /**
     * Builds a class request with the text
     * @param file File with the content to analyze
     * @return A class request object
     * @throws IOException If the parameters passed are incorrect
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ClassRequest withFile(File file) throws IOException, ParameterValidationException {
        return new ClassRequest(transport, key, model, verbose, categoriesFilter, expandHierarchy, new FilePayload(file));
    }

    /**
     * Builds a class request with the url
     * @param url Url with the content to analyze
     * @return A class request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ClassRequest withURL(URL url) throws ParameterValidationException {
        return new ClassRequest(transport, key, model, verbose, categoriesFilter, expandHierarchy, new URLPayload(url.toString()));
    }

    /**
     * Builds a class request with the verbose
     * @param verbose Additional information about the classification
     * @return A class request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ClassRequest withVerbose(boolean verbose) throws ParameterValidationException {
        return new ClassRequest(transport, key, model, verbose, categoriesFilter, expandHierarchy, payload);
    }

    /**
     * Builds a class request with the category
     * @param category categories to which the classification is limited
     * @return A class request object
     * @throws ParameterValidationException Raised when a parameter value can't be accepted
     */
    public ClassRequest withCategoryFilter(String category) throws ParameterValidationException {
        return new ClassRequest(transport, key, model, verbose, category, expandHierarchy, payload);
    }
}
