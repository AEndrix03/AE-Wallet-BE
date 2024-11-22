package com.aendrix.aewallet.services.wallets;

import com.aendrix.aewallet.dto.wallets.EntryDto;
import com.aendrix.aewallet.dto.wallets.EntryFilterDto;
import com.sun.istack.NotNull;

import java.util.List;

public interface EntryService {
    List<EntryDto> getEntriesByWalletId(Long walletId);

    EntryDto getEntryById(Long entryId);

    EntryDto createEntry(EntryDto entryDto);

    EntryDto updateEntry(EntryDto entryDto);

    Long deleteEntry(Long entryId);

    List<EntryDto> getFilteredEntries(@NotNull EntryFilterDto filter);

    List<EntryDto> getAllTimeEntries();
}
