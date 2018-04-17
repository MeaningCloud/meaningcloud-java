package com.meaningcloud;

import java.io.IOException;
import java.util.Map;

public interface Transport {

    String send(Endpoint endpoint, Map<String, String> params) throws IOException;

}
