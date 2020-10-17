package com.tgdz.fkptceshi.tools;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TestHttp {
    public static String HttpRestClient(String url, HttpMethod method, JSONObject json) throws IOException {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(90 * 1000);
        requestFactory.setReadTimeout(90 * 1000);
        RestTemplate client = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        //APPLICATION_PROBLEM_JSON_UTF8
        //headers.setBasicAuth("ghl","ghl");
        Charset charset = StandardCharsets.ISO_8859_1;
        CharsetEncoder encoder = charset.newEncoder();
        String credentialsString = "ghl:ghl";
        byte[] encodedBytes = Base64.getEncoder().encode(credentialsString.getBytes(charset));
        String encodedCredentials = new String(encodedBytes, charset);
        headers.set("Authorization", "Basic " + encodedCredentials);
        //headers.add("ghl","ghl");
        HttpEntity<String> requestEntity = new HttpEntity<String>(json.toString(), headers);
        //  执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }
}
