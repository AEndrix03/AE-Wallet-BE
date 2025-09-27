package it.aredegalli.wallet.service.transaction;

import it.aredegalli.wallet.dto.transaction.TransactionCategoryDto;
import it.aredegalli.wallet.dto.transaction.TransactionDto;
import it.aredegalli.wallet.dto.transaction.TransactionTypeDto;
import it.aredegalli.wallet.dto.transaction.filter.TransactionFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    List<TransactionTypeDto> getAllTransactionTypes();

    List<TransactionCategoryDto> getAllTransactionCategories();

    Page<TransactionDto> getUserTransactionsFiltered(UUID userId,
                                                     TransactionFilterDto filter,
                                                     Pageable pageable);

    Page<TransactionDto> getPortfolioTransactions(UUID portfolioId, Pageable pageable);

    UUID saveTransaction(TransactionDto dto);

    UUID deleteTransaction(UUID id);
}
