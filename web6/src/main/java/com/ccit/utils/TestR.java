package com.ccit.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestR {

    public String testRSS(String word){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        HttpGet httpGet = new HttpGet("http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&key=1587754017&type=data&doctype=xml&version=1.1&q="+word);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            int httpCode = response.getStatusLine().getStatusCode();
            if(httpCode  == 200){
                InputStream inputStream = response.getEntity().getContent();
                result = IOUtils.toString(inputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String testRSS(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        HttpGet httpGet = new HttpGet("http://blog.sina.com.cn/rss/1417190052.xml");
        try {
           HttpResponse response = httpClient.execute(httpGet);
            int httpCode = response.getStatusLine().getStatusCode();
            if(httpCode  == 200){
                InputStream inputStream = response.getEntity().getContent();
                result = IOUtils.toString(inputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public void testPhoto(String url , String savePath){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpclient.execute(httpget);
            int httpcode = response.getStatusLine().getStatusCode();
            if(httpcode == 200) {
                InputStream input = response.getEntity().getContent();
                FileOutputStream output = new FileOutputStream(savePath);
                IOUtils.copy(input,output);
                input.close();
                output.flush();
                output.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void testSounp(){
        try {
            Document document = Jsoup.connect("").get();
            Elements elements = document.select("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
