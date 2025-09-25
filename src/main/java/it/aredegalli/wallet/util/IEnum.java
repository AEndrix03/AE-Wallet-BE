package it.aredegalli.wallet.util;

public interface IEnum {

    String getSymbol();

    /**
     * Decodifica un simbolo nell'enum corrispondente
     * @param enumClass la classe enum
     * @param symbol il simbolo da decodificare
     * @return l'enum corrispondente
     * @throws IllegalArgumentException se il simbolo non è trovato
     */
    static <T extends Enum<T> & IEnum> T decode(Class<T> enumClass, String symbol) {
        if (symbol == null) return null;

        for (T enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getSymbol().equals(symbol)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol + " for enum " + enumClass.getSimpleName());
    }
}