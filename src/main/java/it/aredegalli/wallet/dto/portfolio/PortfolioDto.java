package it.aredegalli.wallet.dto.portfolio;

import it.aredegalli.wallet.enums.CurrencyEnum;
import it.aredegalli.wallet.enums.portfolio.PortfolioTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDto {

    private UUID id;
    private String name;
    private String description;
    private PortfolioTypeEnum type;
    private BigDecimal balance;
    private BigDecimal target;
    private UUID image;
    private CurrencyEnum currency;
    private LocalDateTime lastUpdated;

}
