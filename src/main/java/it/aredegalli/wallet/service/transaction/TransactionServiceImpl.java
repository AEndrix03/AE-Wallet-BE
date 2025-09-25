package it.aredegalli.wallet.service.transaction;

import it.aredegalli.wallet.dto.transaction.TransactionCategoryDto;
import it.aredegalli.wallet.dto.transaction.TransactionTypeDto;
import it.aredegalli.wallet.mapper.transaction.TransactionCategoryMapper;
import it.aredegalli.wallet.mapper.transaction.TransactionTypeMapper;
import it.aredegalli.wallet.repository.transaction.TransactionCategoryRepository;
import it.aredegalli.wallet.repository.transaction.TransactionRepository;
import it.aredegalli.wallet.repository.transaction.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionCategoryRepository transactionCategoryRepository;

    private final TransactionTypeMapper transactionTypeMapper;
    private final TransactionCategoryMapper transactionCategoryMapper;

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

}
