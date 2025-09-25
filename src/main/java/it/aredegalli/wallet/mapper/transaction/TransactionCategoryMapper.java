package it.aredegalli.wallet.mapper.transaction;

import it.aredegalli.wallet.dto.transaction.TransactionCategoryDto;
import it.aredegalli.wallet.entity.transaction.TransactionCategory;
import it.aredegalli.wallet.mapper.base.IDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionCategoryMapper implements IDtoMapper<TransactionCategoryDto, TransactionCategory> {

    public TransactionCategoryDto toDto(TransactionCategory entity) {
        return new TransactionCategoryDto(entity.getCode(), entity.getName());
    }

}
