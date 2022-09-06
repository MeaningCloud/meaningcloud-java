package com.meaningcloud;

public class Endpoint {

    public static class Server {

        /**
         * The default server URL
         */
        public static final String DEFAULT_ADDRESS = "https://api.meaningcloud.com";
        public final String address;

        public Server(String address) {
            this.address = address;
        }

        public Server() {
            this(DEFAULT_ADDRESS);
        }

        public String getAddress() {
            return address;
        }

        public Endpoint with(Service service) {
            return new Endpoint(this, service);
        }
    }

    public final Server server;
    public final String serviceName;

    /**
     * The list of available services.
     */
    public enum Service {
        CLASS_2_0("class-2.0"),
        DEEP_CATEGORIZATION_1_0("deepcategorization-1.0"),
        LANG_2_0("lang-2.0"),
        PARSER_2_0("parser-2.0"),
        SENTIMENT_2_1("sentiment-2.1"),
        TOPICS_2_0("topics-2.0"),

        CLASS(CLASS_2_0),
        DEEP_CATEGORIZATION(DEEP_CATEGORIZATION_1_0),
        LANG(LANG_2_0),
        PARSER(PARSER_2_0),
        SENTIMENT(SENTIMENT_2_1),
        TOPICS(TOPICS_2_0);

        private String endpoint;

        Service(String endpoint) {
            this.endpoint = endpoint;
        }

        Service(Service alias) {
            this.endpoint = alias.endpoint;
        }

        public String getEndpoint() {
            return endpoint;
        }
    }

    /**
     * Builds an endpoint with a given server and endpoint name.
     * This constructor is intended to query on-premises installations
     * or premium services
     * @param server An address like <tt>http://localhost</tt>
     * @param serviceName A name like <tt>class-2.0</tt>
     * @see Service
     */
    private Endpoint(String server, String serviceName) {
        this.server = new Server(server);
        this.serviceName = serviceName;
    }

    /**
     * Builds an endpoint from a given server and a standard service
     * This constructor is indended to query on-premises installations
     * or premium services
     * @param server An address like <tt>http://localhost</tt>
     * @param service A service listed in #Service
     * @see Service
     */
    public Endpoint(Server server, Service service) {
        this(server.getAddress(), service.getEndpoint());
    }

    /**
     * Builds and endpoint from a given standard service.
     * @param service A service listed in #Service
     * @see Service
     */
    public Endpoint(Service service) {
        this(new Server(), service);
    }

    public String getEndpoint() {
        return this.server.address + (this.server.address.endsWith("/") ? "" : "/") + this.serviceName;
    }
}
