package com.example.demo.controller;

import com.example.demo.service.BlockRpcService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BlockRpcController.class)
class BlockRpcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlockRpcService blockRpcService;

    @Test

    void getBlockCount() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("damir","pass123");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String,Object> body = new HashMap<>();
        body.put("result", 2222);
        body.put("error", null);
        body.put("id", 1);

        ResponseEntity<?> responseEntity = new ResponseEntity<>(
                body,
                httpHeaders,
                HttpStatus.OK
        );


        Mockito.when(blockRpcService.getBlockCount()).thenReturn((ResponseEntity<Object>) responseEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bitcoin-testnet-block/block-count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"result\": 2102222,\n" +
                                "    \"error\": null,\n" +
                                "    \"id\": 1\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}