package it.aredegalli.wallet.dto.analytics;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class FinancialSummaryDataDto {

    private final BigDecimal currentBalance;
    private final BigDecimal monthlyIncome;
    private final BigDecimal monthlyExpenses;
    private final BigDecimal netFlow;
    private final Float savingsRate;
    private final Float expenseChange;
    private final Float incomeChange;

    public FinancialSummaryDataDto(BigDecimal currentBalance, BigDecimal monthlyIncome, BigDecimal monthlyExpenses, BigDecimal pastMonthlyIncome, BigDecimal pastMonthlyExpenses) {
        this.netFlow = monthlyIncome.subtract(monthlyExpenses);
        this.savingsRate = monthlyIncome.compareTo(BigDecimal.ZERO) == 0 ? 0f :
                Math.round((monthlyIncome.subtract(monthlyExpenses)).divide(monthlyIncome, 4, RoundingMode.HALF_UP)
                        .floatValue() * 10000) / 100f;
        this.expenseChange = pastMonthlyExpenses.compareTo(BigDecimal.ZERO) == 0 ?
                (monthlyExpenses.compareTo(BigDecimal.ZERO) == 0 ? 0f : 100f) :
                Math.round((monthlyExpenses.subtract(pastMonthlyExpenses))
                        .divide(pastMonthlyExpenses, 4, RoundingMode.HALF_UP)
                        .floatValue() * 10000) / 100f;
        this.incomeChange = pastMonthlyIncome.compareTo(BigDecimal.ZERO) == 0 ?
                (monthlyIncome.compareTo(BigDecimal.ZERO) == 0 ? 0f : 100f) :
                Math.round((monthlyIncome.subtract(pastMonthlyIncome))
                        .divide(pastMonthlyIncome, 4, RoundingMode.HALF_UP)
                        .floatValue() * 10000) / 100f;
        this.currentBalance = currentBalance;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenses = monthlyExpenses;

    }

}
