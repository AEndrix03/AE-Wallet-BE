package it.aredegalli.wallet.controller;

import it.aredegalli.wallet.dto.analytics.CategorySpendingDataDto;
import it.aredegalli.wallet.dto.analytics.FinancialSummaryDataDto;
import it.aredegalli.wallet.dto.analytics.IncomeExpenseDataDto;
import it.aredegalli.wallet.service.analytics.AnalyticService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("analytics")
public class AnalyticsController {

    private final AnalyticService analyticService;

    @GetMapping("kpi")
    public FinancialSummaryDataDto getKpi(@RequestParam() @NotNull UUID userId) {
        return this.analyticService.getKpi(userId);
    }

    @GetMapping("income-expense")
    public List<IncomeExpenseDataDto> getIncomeExpenseData(@RequestParam() @NotNull UUID userId) {
        return this.analyticService.getIncomeExpenseData(userId);
    }

    @GetMapping("category-spending")
    public List<CategorySpendingDataDto> getCategorySpendingData(@RequestParam() @NotNull UUID userId) {
        return this.analyticService.getCategorySpendingData(userId);
    }
    
}
