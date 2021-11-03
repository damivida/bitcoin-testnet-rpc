package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MiningRpcService {

    @Value("${bitcoin.rpc.username}")
    private String username;

    @Value("${bitcoin.rpc.password}")
    private String password;

    @Value("${bitcoin.rpc.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * Method will return mining info
     *
     * @return
     */
    public ResponseEntity<Object> getMiningInfo() {
        Map<String, Object> body = getRequestBody("testRpc", "getmininginfo");

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body,getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url , request, Object.class));

    }


    /**
     * Method creates request body
     *
     * @param id id
     * @param method name of the rcp method
     * @return request body
     */
    public Map<String, Object> getRequestBody(final String id, final String method) {
        Map<String, Object> body = new HashMap<>();
        body.put("jsonrpc", "1.0");
        body.put("id", id);
        body.put("method", method);
        body.put("params", new ArrayList<>());
        return body;
    }

    /**
     * Method creates request header
     *
     * @return request header
     */
    public HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username,password);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;

    }
}
