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

    @Override
    public List<EntryDto> getEntriesByWalletId(Long walletId) {
        return this.entryRepository.getWltEntriesByWltWalletId(walletId).stream()
                .map(WltEntry::toDto)
                .toList();
    }

}
