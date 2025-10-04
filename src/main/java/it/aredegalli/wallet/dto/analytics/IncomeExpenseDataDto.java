package it.aredegalli.wallet.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeExpenseDataDto {

    private String month;
    private LocalDate date;
    private BigDecimal income;
    private BigDecimal expense;
    private BigDecimal netFlow;

}
