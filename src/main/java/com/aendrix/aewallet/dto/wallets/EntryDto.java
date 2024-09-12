package com.aendrix.aewallet.dto.wallets;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class EntryDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private double value;
    private Long walletId;


}
