package it.aredegalli.wallet.enums.transaction;

import it.aredegalli.wallet.util.AbstractEnumConverter;
import it.aredegalli.wallet.util.IEnum;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionTypeEnum implements IEnum {

    INCOME("INC"),
    EXPENSE("EXP"),
    TRANSFER("TRF");

    private final String symbol;

    @Converter(autoApply = true)
    public static class TransactionTypeEnumConverter extends AbstractEnumConverter<TransactionTypeEnum> {
        public TransactionTypeEnumConverter() {
            super(TransactionTypeEnum.class);
        }
    }

}
