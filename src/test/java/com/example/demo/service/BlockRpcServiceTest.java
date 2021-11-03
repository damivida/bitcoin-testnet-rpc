package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
class BlockRpcServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BlockRpcService blockRpcService = new BlockRpcService();


    @Test
    void getBlockCount() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("damir","pass123");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        Map<String, Object> body = new HashMap<>();
        body.put("jsonrpc", "2.0");
        body.put("id", 1);
        body.put("method", "getblockcount");
        body.put("params", new ArrayList<>());

        Map<String,Object> bodyResponse = new HashMap<>();
        bodyResponse.put("result", 2222);
        bodyResponse.put("error", null);
        bodyResponse.put("id", 1);


        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body, httpHeaders);

        Mockito.when(restTemplate.postForObject("http://127.0.0.1:18332",request,Object.class))
                .thenReturn(new ResponseEntity(bodyResponse, HttpStatus.OK));

        Object blockCount = blockRpcService.getBlockCount();
        Assertions.assertEquals(bodyResponse, blockCount);

    }
}