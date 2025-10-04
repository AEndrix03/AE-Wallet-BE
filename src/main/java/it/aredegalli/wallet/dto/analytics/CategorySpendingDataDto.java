package it.aredegalli.wallet.dto.analytics;

import it.aredegalli.wallet.enums.portfolio.PortfolioTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategorySpendingDataDto {

    private PortfolioTypeEnum category;
    private BigDecimal amount;
    private BigDecimal percentage;
    private String color;
    private BigDecimal transactionCount;
}
