package it.aredegalli.wallet.mapper.transaction;

import it.aredegalli.wallet.dto.transaction.TransactionDto;
import it.aredegalli.wallet.entity.transaction.Transaction;
import it.aredegalli.wallet.mapper.base.IDtoMapper;
import it.aredegalli.wallet.mapper.base.IEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements IDtoMapper<TransactionDto, Transaction>, IEntityMapper<TransactionDto, Transaction> {

    @Override
    public TransactionDto toDto(Transaction entity) {
        return TransactionDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .amount(entity.getAmount() != null ? entity.getAmount().doubleValue() : null)
                .currency(entity.getCurrency())
                .type(entity.getType())
                .date(entity.getTransactionDate() != null ? entity.getTransactionDate().atZone(java
                                .time
                                .ZoneId
                                .systemDefault())
                        .toLocalDateTime() : null)
                .portfolioId(entity.getPortfolio() != null ? entity.getPortfolio().getId() : null)
                .portfolioName(entity.getPortfolio() != null ? entity.getPortfolio().getName() :
                        null)
                .note(entity.getNote())
                .build();
    }

    @Override
    public Transaction toEntity(TransactionDto dto) {
        return Transaction.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .amount(dto.getAmount() != null ? java.math.BigDecimal.valueOf(dto.getAmount()) : null)
                .currency(dto.getCurrency())
                .type(dto.getType())
                .transactionDate(dto.getDate() != null ? dto.getDate().atZone(java.time.ZoneId.systemDefault()).toInstant() : null)
                .portfolio(dto.getPortfolioId() != null ? it.aredegalli.wallet.entity.portfolio.Portfolio.builder().id(dto.getPortfolioId()).build() : null)
                .note(dto.getNote())
                .build();
    }
}
