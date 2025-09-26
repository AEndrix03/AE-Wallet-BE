package it.aredegalli.wallet.service.transaction.finder;

import it.aredegalli.wallet.dto.transaction.TransactionDto;
import it.aredegalli.wallet.dto.transaction.filter.TransactionFilterDto;
import it.aredegalli.wallet.entity.transaction.Transaction;
import it.aredegalli.wallet.mapper.transaction.TransactionMapper;
import it.aredegalli.wallet.repository.transaction.TransactionRepository;
import it.aredegalli.wallet.service.transaction.finder.specification.TransactionSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionFinder {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public Page<TransactionDto> getUserTransactionsFiltered(UUID userId,
                                                            TransactionFilterDto filter,
                                                            Pageable pageable) {

        Specification<Transaction> spec = Specification
                .where(TransactionSpecifications.belongsToUser(userId))
                .and(TransactionSpecifications.hasAmountGreaterThan(filter.getAmount()))
                .and(TransactionSpecifications.hasCurrency(filter.getCurrency()))
                .and(TransactionSpecifications.hasCategory(filter.getCategory()))
                .and(TransactionSpecifications.hasType(filter.getType()))
                .and(TransactionSpecifications.createdBetween(filter.getDateFrom(), filter.getDateTo()));

        if (filter.getDescription() == null || filter.getDescription().trim().isEmpty()) {
            return transactionRepository.findAll(spec, pageable).map(this.transactionMapper::toDto);
        }

        List<Transaction> allTransactions = transactionRepository.findAll(spec,
                Sort.by(Sort.Direction.DESC, "creationDate"));

        String searchTerm = filter.getDescription().toLowerCase().trim();
        List<Transaction> filteredByDescription = allTransactions.stream()
                .filter(t -> t.getDescription() != null &&
                        t.getDescription().toLowerCase().contains(searchTerm))
                .toList();

        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min(start + pageable.getPageSize(), filteredByDescription.size());
        List<TransactionDto> pageContent = filteredByDescription.subList(start, end)
                .stream()
                .map(this.transactionMapper::toDto)
                .toList();

        return new PageImpl<>(pageContent, pageable, filteredByDescription.size());
    }

}
