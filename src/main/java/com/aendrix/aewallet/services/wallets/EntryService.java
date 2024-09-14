package com.aendrix.aewallet.services.wallets;

import com.aendrix.aewallet.dto.wallets.EntryDto;

import java.util.List;

public interface EntryService {
    List<EntryDto> getEntriesByWalletId(Long walletId);

    EntryDto getEntryById(Long entryId);

    EntryDto createEntry(EntryDto entryDto);

    EntryDto updateEntry(EntryDto entryDto);

    Long deleteEntry(Long entryId);
}
