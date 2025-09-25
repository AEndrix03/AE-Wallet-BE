package it.aredegalli.wallet.enums.portfolio;

import it.aredegalli.wallet.util.AbstractEnumConverter;
import it.aredegalli.wallet.util.IEnum;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PortfolioStatusEnum implements IEnum {

    HEALTHY("HEA"),
    WARNING("WRN"),
    CRITICAL("CRT"),
    INACTIVE("INA");

    private final String symbol;

    @Converter(autoApply = true)
    public static class PortfolioStatusEnumConverter extends AbstractEnumConverter<PortfolioStatusEnum> {
        public PortfolioStatusEnumConverter() {
            super(PortfolioStatusEnum.class);
        }
    }

}
