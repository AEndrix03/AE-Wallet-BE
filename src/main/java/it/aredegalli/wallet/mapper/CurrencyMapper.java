package it.aredegalli.wallet.mapper;

import it.aredegalli.wallet.dto.CurrencyDto;
import it.aredegalli.wallet.entity.Currency;
import it.aredegalli.wallet.mapper.base.IDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper implements IDtoMapper<CurrencyDto, Currency> {

    public CurrencyDto toDto(Currency entity) {
        return new CurrencyDto(entity.getCode(), entity.getSymbol());
    }

}
