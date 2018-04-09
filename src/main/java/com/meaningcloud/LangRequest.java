package com.meaningcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Represents a Language Identification request
 * You can find more information at https://www.meaningcloud.com/developer/language-identification/doc/2.0/request
 */
public class LangRequest extends Request{
    public static final String ENDPOINT = SERVER.concat("lang-2.0");
    public static final String DEFAULT_SELECTION = " ";
    public static final boolean DEFAULT_VERBOSE = false;
    public static final int DEFAULT_RELEVANCE = 0;
    public static final Payload DEFAULT_PAYLOAD = new NoPayload();

    /**
     * Interface to obtain parameters
     */
    interface Payload{
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
        public Map<String, String> getParams() {
            throw new RuntimeException("No payload defined");
        }
    }

    public final String selection;
    public final boolean verbose;
    public final int relevance;
    public final Payload payload;

    /**
     * Add parameters to the request
     * @return A lang response object
     * @throws IOException Raised when a parameter value can't be accepted
     */
    public LangResponse send() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("src", "mc-java");
        params.put("selection", selection);
        params.put("verbose", verbose ? "y" : "n");

        for (Map.Entry<String, String> x : payload.getParams().entrySet()) {
            params.put(x.getKey(), x.getValue());
        }

        String response = post(ENDPOINT, params);
        return LangResponse.from(response);
    }

    /**
     * LangRequest constructor
     * @param key User's API key
     * @param selection Expected languages
     * @param verbose Shows additional information about the languages detected
     * @param relevance Relevance value associated to it
     * @param payload Interface to obtain parameters
     */
    public LangRequest(String key,
                       String selection,
                       boolean verbose,
                       int relevance,
                       Payload payload) {
        super(key);
        this.selection = selection;
        this.verbose = verbose;
        this.relevance = relevance;
        this.payload = payload;
    }

    /**
     * Builds a lang request with the given API key
     * @param key User's API key
     * @return A lang request object
     */
    public static LangRequest build(String key){
        return new LangRequest(key, DEFAULT_SELECTION, DEFAULT_VERBOSE, DEFAULT_RELEVANCE, DEFAULT_PAYLOAD);
    }

    /**
     * Builds a lang request with the verbose
     * @param verbose Shows additional information about the languages detected
     * @return A lang request object
     */
    public LangRequest withVerbose(boolean verbose) {
        return new LangRequest(key, selection, verbose, relevance, payload);
    }

    /**
     * Builds a lang request with the text
     * @param txt Input text
     * @return A lang request object
     */
    public LangRequest withText(String txt) {
        return new LangRequest(key, selection, verbose, relevance, new TextPayload(txt));
    }

    /**
     * Builds a lang request with the selection
     * @param selection Expected languages
     * @return A lang request object
     */
    public LangRequest withSelection(String selection) {
        return new LangRequest(key, selection, verbose, relevance, payload);
    }

    /**
     * Builds a lang request with file
     * @param file File with the content to analyze
     * @return A lang request object
     * @throws IOException Raised when a parameter value can't be accepted
     */
     public LangRequest withFile(File file) throws IOException {
        return new LangRequest(key, selection, verbose, relevance, new FilePayload(file));
    }

    /**
     * Builds a lang request with url
     * @param url Url of the content to analyze
     * @return A lang request object
     * @throws IOException Raised when a parameter value can't be accepted
     */
    public LangRequest withURL(URL url) throws IOException {
        return new LangRequest(key, selection, verbose, relevance, new URLPayload(url.toString()));
    }
}
