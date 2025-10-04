package it.aredegalli.wallet.service.analytics.processor;

import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
import it.aredegalli.wallet.repository.portfolio.PortfolioRepository;
import it.aredegalli.wallet.repository.transaction.TransactionRepository;
import it.aredegalli.wallet.service.analytics.dto.PortfolioTypeSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AnalyticStatsProcessor {

    private final TransactionRepository transactionRepository;
    private final PortfolioRepository portfolioRepository;
    private final LocaleResolver localeResolver;

    public BigDecimal getTotalBalance(UUID userId) {
        return transactionRepository.sumAmountsByUserId(userId);
    }

    public BigDecimal getTotalMonthlyIncomes(UUID userId, LocalDate fromDate, LocalDate toDate) {
        return transactionRepository.sumAmountsByUserIdAndTypeAndDate(userId, TransactionTypeEnum.INCOME, fromDate.atStartOfDay(ZoneOffset.UTC).toInstant(), toDate.atTime(LocalTime.MAX).atZone(ZoneOffset.UTC).toInstant()).abs();
    }

    public BigDecimal getTotalMonthlyExpenses(UUID userId, LocalDate fromDate, LocalDate toDate) {
        return transactionRepository.sumAmountsByUserIdAndTypeAndDate(userId, TransactionTypeEnum.EXPENSE, fromDate.atStartOfDay(ZoneOffset.UTC).toInstant(), toDate.atTime(LocalTime.MAX).atZone(ZoneOffset.UTC).toInstant()).abs();
    }

    public List<PortfolioTypeSummary> getBalanceByPortfolioType(UUID userId) {
        return transactionRepository.sumAmountsByUserIdAndPortfolioType(userId);
    }


}
