package it.aredegalli.wallet.dto.transaction;

import it.aredegalli.wallet.enums.transaction.TransactionCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionCategoryDto {

    private TransactionCategoryEnum code;
    private String description;

}
