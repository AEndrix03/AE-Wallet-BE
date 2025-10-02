package it.aredegalli.wallet.dto.transaction;

import it.aredegalli.wallet.enums.CurrencyEnum;
import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private UUID id;
    private String description;
    private Double amount;
    private CurrencyEnum currency;
    private TransactionTypeEnum type;
    private LocalDateTime date;
    private UUID portfolioId;
    private String portfolioName;
    private String note;

}
