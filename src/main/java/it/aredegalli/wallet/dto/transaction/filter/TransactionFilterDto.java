package it.aredegalli.wallet.dto.transaction.filter;

import it.aredegalli.wallet.enums.CurrencyEnum;
import it.aredegalli.wallet.enums.transaction.TransactionCategoryEnum;
import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFilterDto {

    private String description;
    private Double amount;
    private CurrencyEnum currency;
    private TransactionCategoryEnum category;
    private TransactionTypeEnum type;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

}
