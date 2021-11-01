package com.example.demo.controller;

import com.example.demo.service.BlockRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/bitcoin-testnet-block")
public class BlockRpcController {

    private final BlockRpcService blockRpcService;

    public BlockRpcController(BlockRpcService blockRpcService) {
        this.blockRpcService = blockRpcService;
    }

    @GetMapping("/block-count")
    private final ResponseEntity<Object> getBlockCount() {
        return blockRpcService.getBlockCount();
    }

}
