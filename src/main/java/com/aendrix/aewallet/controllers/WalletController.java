package com.aendrix.aewallet.controllers;

import com.aendrix.aewallet.dto.wallets.WalletCreateDto;
import com.aendrix.aewallet.dto.wallets.WalletDto;
import com.aendrix.aewallet.services.wallets.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/user")
    public List<WalletDto> getUserWallets() {
        return this.walletService.getUserWallets();
    }

    @GetMapping("/user/{walletId}")
    public WalletDto getWalletById(@PathVariable(name = "walletId") Long walletId) {
        return this.walletService.getWalletById(walletId);
    }

    @PostMapping()
    public Long createWallet(@RequestBody WalletCreateDto walletDto) {
        return this.walletService.createWallet(walletDto).getId();
    }

}
