package com.aendrix.aewallet.dto.wallets;

import lombok.Data;

@Data
public class WalletCreateDto {

    private String name;
    private String description;
    private Long userId;
    private String headerColor;
    private String headerBackgroundColor;

}
