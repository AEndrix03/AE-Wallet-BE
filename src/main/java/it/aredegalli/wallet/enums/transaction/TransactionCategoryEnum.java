package it.aredegalli.wallet.enums.transaction;

import it.aredegalli.wallet.util.AbstractEnumConverter;
import it.aredegalli.wallet.util.IEnum;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionCategoryEnum implements IEnum {
    
    CHECKING("CHK"),
    SAVINGS("SAV"),
    INVESTMENT("INV"),
    CRYPTO("CRY"),
    CASH("CAS"),
    CREDIT("CRE"),
    PAC("PAC"),
    EMERGENCY("EME");

    private final String symbol;

    @Converter(autoApply = true)
    public static class TransactionCategoryEnumConverter extends AbstractEnumConverter<TransactionCategoryEnum> {
        public TransactionCategoryEnumConverter() {
            super(TransactionCategoryEnum.class);
        }
    }

}
