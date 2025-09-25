package it.aredegalli.wallet.dto;

import it.aredegalli.wallet.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyDto {

    private CurrencyEnum code;
    private String symbol;

}
