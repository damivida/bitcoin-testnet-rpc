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
public class WalletRpcService {


    @Value("${bitcoin.rpc.username}")
    private String username;

    @Value("${bitcoin.rpc.password}")
    private String password;

    @Value("${bitcoin.rpc.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Method returns list of wallet names
     *
     * @param id rpc request id
     * @return
     */
    public ResponseEntity<Object> getWalletList(String id) {
        Map<String, Object> body = getRequestBody(id, "listwallets");
        body.put("params", new ArrayList<>());

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body,getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    /**
     * Method generates new wallet
     *
     * @param id rpc request id
     * @param walletName wallet name
     * @return created wallet
     */
    public ResponseEntity<Object> createWallet(String id, String walletName) {
        Map<String, Object> body = getRequestBody(id, "createwallet");

        ArrayList walletNameList = new ArrayList();
        walletNameList.add(walletName);

        body.put("params", walletNameList);

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body,getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }



    /**
     * Method creates request body
     *
     * @param id rpc request id
     * @param method name of the rcp method
     * @return request body
     */
    public Map<String, Object> getRequestBody(final String id, final String method) {
        Map<String, Object> body = new HashMap<>();
        body.put("jsonrpc", "1.0");
        body.put("id", id);
        body.put("method", method);
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
