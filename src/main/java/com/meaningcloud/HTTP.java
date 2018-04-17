package com.meaningcloud;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HTTP implements Transport {

    @Override
    public String send(Endpoint endpoint, Map<String, String> params) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(endpoint.getEndpoint());
        post.setHeader("User-Agent", "MeaningCloud Java client");
        List<NameValuePair> urlParameters = new ArrayList<>();
        for (Map.Entry<String, String> x : params.entrySet()) {
            urlParameters.add(new BasicNameValuePair(x.getKey(), x.getValue()));
        }
        UrlEncodedFormEntity x = new UrlEncodedFormEntity(urlParameters, Consts.UTF_8);
        x.setContentEncoding("utf-8");
        post.setEntity(x);

        CloseableHttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
