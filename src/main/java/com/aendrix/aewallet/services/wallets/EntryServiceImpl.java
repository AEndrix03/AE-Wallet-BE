package com.aendrix.aewallet.services.wallets;

import com.aendrix.aewallet.dto.wallets.EntryDto;
import com.aendrix.aewallet.entity.WltEntry;
import com.aendrix.aewallet.repositories.wallets.EntryRepository;
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

}
