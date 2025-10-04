package it.aredegalli.wallet.service.analytics;

import it.aredegalli.wallet.dto.analytics.CategorySpendingDataDto;
import it.aredegalli.wallet.dto.analytics.FinancialSummaryDataDto;
import it.aredegalli.wallet.dto.analytics.IncomeExpenseDataDto;
import it.aredegalli.wallet.service.analytics.dto.PortfolioTypeSummary;
import it.aredegalli.wallet.service.analytics.processor.AnalyticStatsProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnalyticServiceImpl implements AnalyticService {

    private final AnalyticStatsProcessor analyticStatsProcessor;

    @Override
    public FinancialSummaryDataDto getKpi(UUID userId) {
        LocalDate month = LocalDate.now();
        LocalDate pastMonth = LocalDate.now().minusMonths(1);

        return new FinancialSummaryDataDto(
                analyticStatsProcessor.getTotalBalance(userId),
                analyticStatsProcessor.getTotalMonthlyIncomes(userId, month.withDayOfMonth(1), month.withDayOfMonth(month.lengthOfMonth())),
                analyticStatsProcessor.getTotalMonthlyExpenses(userId, month.withDayOfMonth(1), month.withDayOfMonth(month.lengthOfMonth())),
                analyticStatsProcessor.getTotalMonthlyIncomes(userId, pastMonth.withDayOfMonth(1), pastMonth.withDayOfMonth(pastMonth.lengthOfMonth())),
                analyticStatsProcessor.getTotalMonthlyIncomes(userId, pastMonth.withDayOfMonth(1), pastMonth.withDayOfMonth(pastMonth.lengthOfMonth()))
        );
    }

    @Override
    public List<IncomeExpenseDataDto> getIncomeExpenseData(UUID userId) {
        ArrayList<IncomeExpenseDataDto> last6Months = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            LocalDate start = LocalDate.now().minusMonths(i).withDayOfMonth(1);
            LocalDate end = LocalDate.now().minusMonths(i).withDayOfMonth(LocalDate.now().minusMonths(i).lengthOfMonth());

            BigDecimal income = analyticStatsProcessor.getTotalMonthlyIncomes(userId, start, end);
            BigDecimal expense = analyticStatsProcessor.getTotalMonthlyExpenses(userId, start, end);

            last6Months.add(
                    IncomeExpenseDataDto.builder()
                            .month(end.getMonth().name())
                            .date(end)
                            .income(income)
                            .expense(expense)
                            .netFlow(income.subtract(expense))
                            .build()
            );
        }

        return last6Months;
    }

    @Override
    public List<CategorySpendingDataDto> getCategorySpendingData(UUID userId) {
        List<PortfolioTypeSummary> summaries = analyticStatsProcessor.getBalanceByPortfolioType(userId);
        BigDecimal totalIncomes = analyticStatsProcessor.getTotalBalance(userId);

        return summaries.stream()
                .map(s -> {
                    BigDecimal percentage = totalIncomes.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO :
                            s.getTotalAmount().abs()
                                    .multiply(BigDecimal.valueOf(100))
                                    .divide(totalIncomes, 2, RoundingMode.HALF_UP);
                    return CategorySpendingDataDto.builder()
                            .category(s.getPortfolioType())
                            .amount(s.getTotalAmount().abs())
                            .percentage(percentage)
                            .color(null) // TODO
                            .build();
                }).toList();
    }

}
