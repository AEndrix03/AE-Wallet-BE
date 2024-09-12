package com.aendrix.aewallet.services.wallets;

import com.aendrix.aewallet.dto.wallets.WalletCreateDto;
import com.aendrix.aewallet.dto.wallets.WalletDto;

import java.util.List;

public interface WalletService {
    WalletDto getWalletById(Long walletId);

    List<WalletDto> getUserWallets(Long userId);

    WalletDto createWallet(WalletCreateDto walletCreateDto);

    List<WalletDto> getUserWallets();

    Long deleteWallet(Long walletId);

    Double getWalletBalance(Long walletId);
}
