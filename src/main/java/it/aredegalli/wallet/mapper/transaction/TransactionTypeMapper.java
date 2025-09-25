package it.aredegalli.wallet.mapper.transaction;

import it.aredegalli.wallet.dto.transaction.TransactionTypeDto;
import it.aredegalli.wallet.entity.transaction.TransactionType;
import it.aredegalli.wallet.mapper.base.IDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionTypeMapper implements IDtoMapper<TransactionTypeDto, TransactionType> {

    public TransactionTypeDto toDto(TransactionType entity) {
        return new TransactionTypeDto(entity.getCode(), entity.getName());
    }

}
