package com.aendrix.aewallet.repositories.wallets;

import com.aendrix.aewallet.entity.WltEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<WltEntry, Long> {

    List<WltEntry> getWltEntriesByWltWalletIdAndDeletedFalse(Long walletId);

    @Query("SELECT SUM(e.value) FROM WltEntry e WHERE e.wltWallet.id = ?1 AND e.deleted = false")
    Double getWalletBalance(Long walletId);

    @Query("SELECT e FROM WltEntry e WHERE e.wltWallet.wltUser.id = ?1 AND e.deleted = false AND e.wltWallet.deleted = false")
    List<WltEntry> getAllTimeEntries(Long userId);

}
