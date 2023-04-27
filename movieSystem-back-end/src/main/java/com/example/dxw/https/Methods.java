package com.example.dxw.https;



import org.apache.http.Consts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Methods {
    public static String generalGet(String urlStr) {
        TemplateProxy templateProxy = new TemplateProxy();
        RestTemplate restTemplate = templateProxy.setProxy();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(urlStr, String.class);
        System.out.println(forEntity);
        String body = forEntity.getBody();
//        System.out.println(body);
        return body;
    }

}