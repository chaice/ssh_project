package com.ccit.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
public class Result {
    public String getResult(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        String result = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            int httpCode = response.getStatusLine().getStatusCode();
            if (httpCode == 200) {
                InputStream in = response.getEntity().getContent();
                result = IOUtils.toString(in);
            }
        } catch (IOException e) {
            new RuntimeException(e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }
        return result;
    }
}
