package com.aendrix.aewallet.repositories.wallets;

import com.aendrix.aewallet.entity.WltEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<WltEntry, Long> {

    List<WltEntry> getWltEntriesByWltWalletId(Long walletId);

}
