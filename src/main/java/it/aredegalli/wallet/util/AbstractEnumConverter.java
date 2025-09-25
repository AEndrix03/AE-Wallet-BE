package it.aredegalli.wallet.util;

import jakarta.persistence.AttributeConverter;

/**
 * Converter astratto per enum che implementano IEnum
 * @param <T> il tipo enum che estende IEnum
 */
public abstract class AbstractEnumConverter<T extends Enum<T> & IEnum> implements AttributeConverter<T, String> {

    private final Class<T> enumClass;

    protected AbstractEnumConverter(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(T enumValue) {
        return enumValue != null ? enumValue.getSymbol() : null;
    }

    @Override
    public T convertToEntityAttribute(String symbol) {
        return IEnum.decode(enumClass, symbol);
    }
}