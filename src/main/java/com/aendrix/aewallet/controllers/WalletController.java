package com.aendrix.aewallet.controllers;

import com.aendrix.aewallet.dto.wallets.*;
import com.aendrix.aewallet.services.wallets.EntryService;
import com.aendrix.aewallet.services.wallets.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private EntryService entryService;

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

    @DeleteMapping("/{walletId}")
    public Long deleteWallet(@PathVariable(name = "walletId") Long walletId) {
        return this.walletService.deleteWallet(walletId);
    }

    @GetMapping("/{walletId}/entries")
    public List<EntryDto> getEntriesByWalletId(@PathVariable(name = "walletId") Long walletId) {
        return this.entryService.getEntriesByWalletId(walletId);
    }

    @PutMapping()
    public Long updateWallet(@RequestBody WalletUpdateDto walletDto) {
        return this.walletService.updateWallet(walletDto);
    }

    @GetMapping("/entry/{entryId}")
    public EntryDto getEntryById(@PathVariable(name = "entryId") Long entryId) {
        return this.entryService.getEntryById(entryId);
    }

    @PostMapping("/entry")
    public Long createEntry(@RequestBody EntryDto entryDto) {
        return this.entryService.createEntry(entryDto).getId();
    }

    @PutMapping("/entry")
    public Long updateEntry(@RequestBody EntryDto entryDto) {
        return this.entryService.updateEntry(entryDto).getId();
    }

    @DeleteMapping("/entry/{entryId}")
    public Long deleteEntry(@PathVariable(name = "entryId") Long entryId) {
        return this.entryService.deleteEntry(entryId);
    }

    @GetMapping("/{walletId}/balance")
    public Double getWalletBalance(@PathVariable(name = "walletId") Long walletId) {
        return this.walletService.getWalletBalance(walletId);
    }

    @PostMapping("/entries/filter")
    public List<EntryDto> getFilteredEntries(@RequestBody EntryFilterDto filter) {
        return this.entryService.getFilteredEntries(filter);
    }

}

