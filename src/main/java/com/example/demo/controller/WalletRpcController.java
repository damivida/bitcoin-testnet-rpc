package com.example.demo.controller;

import com.example.demo.service.WalletRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@Slf4j
@RestController
@RequestMapping(path = "/api/v1/bitcoin-testnet-wallet")
public class WalletRpcController {

    private final WalletRpcService walletRpcService;

    public WalletRpcController(WalletRpcService walletRpcService) {
        this.walletRpcService = walletRpcService;
    }

    /**
     * List wallet names
     *
     * @param id  rpc request id
     * @return list of wallet names
     */
    @GetMapping("/wallet-list")
    private final ResponseEntity<Object> getWalletList(@RequestParam String id) {
        return walletRpcService.getWalletList(id);
    }

    /**
     * Method will create new wallet
     *
     * @param id rpc request id
     * @param walletName wallet name
     * @return new created wallet
     */
    @PostMapping("/wallet")
    private final ResponseEntity<Object> createWallet(@RequestParam String id,
                                                      @RequestParam String walletName) {
        return walletRpcService.createWallet(id, walletName);
    }
}
