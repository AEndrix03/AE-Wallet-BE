package com.aendrix.aewallet.dto.wallets;

import com.aendrix.aewallet.entity.WltWallet;
import com.aendrix.aewallet.utils.EntityMapper;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class WalletDto implements EntityMapper<WltWallet> {

    private Long id;
    private String name;
    private String description;
    private String headerColor;
    private String headerBackgroundColor;

    @Override
    public WltWallet toEntity() {
        return WltWallet.builder()
                .id(id)
                .name(name)
                .description(description)
                .headerColor(headerColor)
                .headerBackgroundColor(headerBackgroundColor)
                .build();
    }
}
