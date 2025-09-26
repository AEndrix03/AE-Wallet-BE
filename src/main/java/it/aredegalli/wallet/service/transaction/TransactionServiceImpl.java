package it.aredegalli.wallet.service.transaction;

import it.aredegalli.wallet.dto.transaction.TransactionCategoryDto;
import it.aredegalli.wallet.dto.transaction.TransactionDto;
import it.aredegalli.wallet.dto.transaction.TransactionTypeDto;
import it.aredegalli.wallet.dto.transaction.filter.TransactionFilterDto;
import it.aredegalli.wallet.mapper.transaction.TransactionCategoryMapper;
import it.aredegalli.wallet.mapper.transaction.TransactionMapper;
import it.aredegalli.wallet.mapper.transaction.TransactionTypeMapper;
import it.aredegalli.wallet.repository.transaction.TransactionCategoryRepository;
import it.aredegalli.wallet.repository.transaction.TransactionRepository;
import it.aredegalli.wallet.repository.transaction.TransactionTypeRepository;
import it.aredegalli.wallet.service.transaction.finder.TransactionFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionCategoryRepository transactionCategoryRepository;

    private final TransactionMapper transactionMapper;
    private final TransactionTypeMapper transactionTypeMapper;
    private final TransactionCategoryMapper transactionCategoryMapper;

    private final TransactionFinder transactionFinder;

    @Override
    public List<TransactionTypeDto> getAllTransactionTypes() {
        return this.transactionTypeRepository.findAll().stream()
                .map(transactionTypeMapper::toDto)
                .toList();
    }

    @Override
    public List<TransactionCategoryDto> getAllTransactionCategories() {
        return this.transactionCategoryRepository.findAll().stream()
                .map(transactionCategoryMapper::toDto)
                .toList();
    }

    @Override
    public Page<TransactionDto> getUserTransactionsFiltered(UUID userId,
                                                            TransactionFilterDto filter,
                                                            Pageable pageable) {

        return this.transactionFinder.getUserTransactionsFiltered(userId, filter, pageable);
    }

    @Override
    public Page<TransactionDto> getPortfolioTransactions(UUID portfolioId, Pageable pageable) {
        return this.transactionRepository.findByPortfolioIdOrderByCreationDateDesc(portfolioId, pageable)
                .map(transactionMapper::toDto);
    }


}
