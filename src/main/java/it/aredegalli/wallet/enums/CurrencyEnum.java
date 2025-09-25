package it.aredegalli.wallet.enums;

import it.aredegalli.wallet.util.AbstractEnumConverter;
import it.aredegalli.wallet.util.IEnum;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyEnum implements IEnum {

    EUR("€"), USD("$"), GBP("£"), JPY("¥");

    private final String symbol;

    @Converter(autoApply = true)
    public static class CurrencyEnumConverter extends AbstractEnumConverter<CurrencyEnum> {
        public CurrencyEnumConverter() {
            super(CurrencyEnum.class);
        }
    }

}
