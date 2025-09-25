package it.aredegalli.wallet.dto.transaction;

import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionTypeDto {

    private TransactionTypeEnum code;
    private String description;

}
