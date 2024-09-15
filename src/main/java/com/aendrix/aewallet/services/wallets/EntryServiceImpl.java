package com.aendrix.aewallet.services.wallets;

import com.aendrix.aewallet.dto.wallets.EntryDto;
import com.aendrix.aewallet.dto.wallets.EntryFilterDto;
import com.aendrix.aewallet.entity.WltEntry;
import com.aendrix.aewallet.repositories.wallets.EntryRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private WalletService walletService;

    @Override
    public List<EntryDto> getEntriesByWalletId(Long walletId) {
        return this.entryRepository.getWltEntriesByWltWalletIdAndDeletedFalse(walletId).stream()
                .map(WltEntry::toDto)
                .toList();
    }

    @Override
    public EntryDto getEntryById(Long entryId) {
        return this.entryRepository.findById(entryId).map(WltEntry::toDto).orElse(null);
    }

    @Override
    public EntryDto createEntry(EntryDto entryDto) {
        WltEntry entry = new WltEntry();
        entry.setTitle(entryDto.getTitle());
        entry.setDescription(entryDto.getDescription());
        entry.setDate(entryDto.getDate());
        entry.setValue(entryDto.getValue());
        entry.setWltWallet(this.walletService.getWalletById(entryDto.getWalletId()).toEntity());
        entry.setDeleted(false);
        entry = this.entryRepository.save(entry);
        return entry.toDto();
    }

    @Override
    public EntryDto updateEntry(EntryDto entryDto) {
        WltEntry entry = this.entryRepository.findById(entryDto.getId()).orElse(null);
        assert entry != null;
        entry.setTitle(entryDto.getTitle());
        entry.setDescription(entryDto.getDescription());
        entry.setDate(entryDto.getDate());
        entry.setValue(entryDto.getValue());
        entry = this.entryRepository.save(entry);
        return entry.toDto();
    }

    @Override
    public Long deleteEntry(Long entryId) {
        WltEntry entry = this.entryRepository.findById(entryId).orElse(null);
        if (entry != null) {
            entry.setDeleted(true);
            return this.entryRepository.save(entry).getId();
        }
        return null;
    }

    @Override
    public List<EntryDto> getFilteredEntries(@NotNull EntryFilterDto filter) {
        List<EntryDto> entries = this.getEntriesByWalletId(filter.getWalletId());

        if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
            entries = entries.stream().filter(entry -> entry.getTitle().toLowerCase().contains(filter.getTitle().toLowerCase())).toList();
        }

        if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
            entries = entries.stream().filter(entry -> entry.getDescription().toLowerCase().contains(filter.getDescription().toLowerCase())).toList();
        }

        if (filter.getDateFrom() != null) {
            entries = entries.stream().filter(entry -> entry.getDate().isAfter(filter.getDateFrom())).toList();
        }

        if (filter.getDateTo() != null) {
            entries = entries.stream().filter(entry -> entry.getDate().isBefore(filter.getDateTo())).toList();
        }

        if (filter.getValueFrom() != null) {
            entries = entries.stream().filter(entry -> entry.getValue() >= filter.getValueFrom()).toList();
        }

        if (filter.getValueTo() != null) {
            entries = entries.stream().filter(entry -> entry.getValue() <= filter.getValueTo()).toList();
        }

        return entries;
    }

}
