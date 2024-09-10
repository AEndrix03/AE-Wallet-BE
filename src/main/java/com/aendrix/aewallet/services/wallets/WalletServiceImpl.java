package com.aendrix.aewallet.services.wallets;

import com.aendrix.aewallet.dto.wallets.WalletCreateDto;
import com.aendrix.aewallet.dto.wallets.WalletDto;
import com.aendrix.aewallet.entity.WltUser;
import com.aendrix.aewallet.entity.WltWallet;
import com.aendrix.aewallet.repositories.auth.UserRepository;
import com.aendrix.aewallet.repositories.wallets.WalletRepository;
import com.aendrix.aewallet.services.UserProvider;
import com.aendrix.aewallet.services.security.AESCryptoService;
import com.aendrix.aewallet.utils.AEWltCostants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AESCryptoService cryptoService;

    @Autowired
    private UserProvider userProvider;

    @Override
    public WalletDto getWalletById(Long walletId) {
        return this.walletRepository.findById(walletId).map(WltWallet::toDto).orElse(null);
    }

    @Override
    public List<WalletDto> getUserWallets(Long userId) {
        return this.walletRepository.getWalletsByWltUserIdAndDeleted(userId, false).stream().map(WltWallet::toDto).toList();
    }

    @Override
    public WalletDto createWallet(WalletCreateDto walletCreateDto) {
        WltUser user = this.userProvider.getUserDto().toEntity();

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        WltWallet wltWallet = new WltWallet();
        wltWallet.setName(walletCreateDto.getName());
        wltWallet.setDescription(walletCreateDto.getDescription());
        wltWallet.setHeaderColor(walletCreateDto.getHeaderColor() != null ? walletCreateDto.getHeaderColor() : AEWltCostants.DEFAULT_HEADER_COLOR);
        wltWallet.setHeaderBackgroundColor(walletCreateDto.getHeaderBackgroundColor() != null ? walletCreateDto.getHeaderBackgroundColor() : AEWltCostants.DEFAULT_HEADER_BACKGROUND_COLOR);
        wltWallet.setWltUser(user);
        wltWallet.setDeleted(false);
        wltWallet = this.walletRepository.save(wltWallet);
        return wltWallet.toDto();
    }

    @Override
    public List<WalletDto> getUserWallets() {
        return this.walletRepository.getWalletsByWltUserIdAndDeleted(this.userProvider.getUserDto().getId(), false).stream().map(WltWallet::toDto).toList();
    }

    @Override
    public Long deleteWallet(Long walletId) {
        WltWallet wallet = this.walletRepository.findById(walletId).orElse(null);
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet not found");
        }
        wallet.setDeleted(true);
        return this.walletRepository.save(wallet).getId();
    }
}
