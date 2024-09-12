package com.aendrix.aewallet.services.wallets;

import com.aendrix.aewallet.dto.wallets.EntryDto;

import java.util.List;

public interface EntryService {
    List<EntryDto> getEntriesByWalletId(Long walletId);
}
