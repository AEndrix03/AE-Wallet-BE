package it.aredegalli.wallet.service.analytics;

import it.aredegalli.wallet.dto.analytics.CategorySpendingDataDto;
import it.aredegalli.wallet.dto.analytics.FinancialSummaryDataDto;
import it.aredegalli.wallet.dto.analytics.IncomeExpenseDataDto;

import java.util.List;
import java.util.UUID;

public interface AnalyticService {
    FinancialSummaryDataDto getKpi(UUID userId);

    List<IncomeExpenseDataDto> getIncomeExpenseData(UUID userId);

    List<CategorySpendingDataDto> getCategorySpendingData(UUID userId);
}
