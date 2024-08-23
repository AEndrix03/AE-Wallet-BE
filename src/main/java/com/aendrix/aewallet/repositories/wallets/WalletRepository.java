package com.aendrix.aewallet.repositories.wallets;

import com.aendrix.aewallet.entity.WltWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<WltWallet, Long> {

    List<WltWallet> getWalletsByUserId(Long userId);

}
