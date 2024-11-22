package com.aendrix.aewallet.dto.wallets;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntryFilterDto {

    private String title;
    private String description;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Float valueFrom;
    private Float valueTo;
    private Long walletId;

}
