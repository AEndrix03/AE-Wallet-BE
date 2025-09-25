package it.aredegalli.wallet.service.transaction;

import it.aredegalli.wallet.dto.transaction.TransactionCategoryDto;
import it.aredegalli.wallet.dto.transaction.TransactionTypeDto;

import java.util.List;

public interface TransactionService {

    List<TransactionTypeDto> getAllTransactionTypes();

    List<TransactionCategoryDto> getAllTransactionCategories();
}
