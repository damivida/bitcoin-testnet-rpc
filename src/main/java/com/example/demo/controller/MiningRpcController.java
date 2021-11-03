package com.example.demo.controller;

import com.example.demo.service.MiningRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/bitcoin-testnet-mining")
public class MiningRpcController {

    private final MiningRpcService miningRpcService;

    public MiningRpcController(MiningRpcService miningRpcService) {
        this.miningRpcService = miningRpcService;
    }


    @GetMapping("/mininginfo")
    private final ResponseEntity<Object> getMiningInfo() {
        return miningRpcService.getMiningInfo();
    }
}
