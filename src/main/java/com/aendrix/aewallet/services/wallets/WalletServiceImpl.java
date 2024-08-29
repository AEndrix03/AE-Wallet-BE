package com.aendrix.aewallet.services.wallets;

import com.aendrix.aewallet.dto.wallets.WalletCreateDto;
import com.aendrix.aewallet.dto.wallets.WalletDto;
import com.aendrix.aewallet.entity.WltUser;
import com.aendrix.aewallet.entity.WltWallet;
import com.aendrix.aewallet.repositories.auth.UserRepository;
import com.aendrix.aewallet.repositories.wallets.WalletRepository;
import com.aendrix.aewallet.services.security.AESCryptoService;
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

    @Override
    public WalletDto getWalletById(Long walletId) {
        return this.walletRepository.findById(walletId).map(WltWallet::toDto).orElse(null);
    }

    @Override
    public List<WalletDto> getUserWallets(Long userId) {
        return this.walletRepository.getWalletsByWltUserId(userId).stream().map(WltWallet::toDto).toList();
    }

    @Override
    public WalletDto createWallet(WalletCreateDto walletCreateDto) {
        WltUser user = this.userRepository.findById(walletCreateDto.getUserId()).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        WltWallet wltWallet = new WltWallet();
        wltWallet.setName(walletCreateDto.getName());
        wltWallet.setDescription(walletCreateDto.getDescription());
        wltWallet.setWltUser(user);
        wltWallet = this.walletRepository.save(wltWallet);
        return wltWallet.toDto();
    }
}
