package it.aredegalli.wallet.service.analytics.dto;

import it.aredegalli.wallet.enums.portfolio.PortfolioTypeEnum;

import java.math.BigDecimal;

public interface PortfolioTypeSummary {
    PortfolioTypeEnum getPortfolioType();

    BigDecimal getTotalAmount();
}