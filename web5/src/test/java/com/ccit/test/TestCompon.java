package com.ccit.test;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class TestCompon {
    @Test
    public void testCom(){
        CloseableHttpClient client = HttpClients.createDefault();
        String result = null;
        try {
            HttpGet httpGet = new HttpGet("http://blog.sina.com.cn/rss/1220218113.xml");
            HttpResponse response = client.execute(httpGet);
            int httpCode = response.getStatusLine().getStatusCode();
            if (httpCode == 200) {
                InputStream in = response.getEntity().getContent();
                result = IOUtils.toString(in);
            }
        } catch (IOException e) {
            new RuntimeException(e);
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }
        System.out.println(result);

    }

    public void testPhoto(String url , String savePath){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            int httpCode = response.getStatusLine().getStatusCode();
            if (httpCode == 200) {
                InputStream in = response.getEntity().getContent();
                FileOutputStream fileOutputStream = new FileOutputStream(savePath);
                IOUtils.copy(in,fileOutputStream);

                in.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            new RuntimeException(e);
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }


    }
    @Test
    //爬虫
    public void testSoup() throws IOException {
        for (int i =1;i<5;i++) {
            Document document = Jsoup.connect("http://www.topit.me/pop?p=" + i).cookie("is_click", "1").get();
            System.out.println(document.title());
            Elements elements = document.select("#content .catalog .e>a");
            for (Element element : elements) {
                String href = element.attr("href");
                Document image = Jsoup.connect(href).cookie("is_click", "1").get();
                Element element1 = image.select("#content>a").first();
                String url = element1.attr("href");
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                testPhoto(url, "d:/photo/" + fileName);

            }
        }

    }

}


