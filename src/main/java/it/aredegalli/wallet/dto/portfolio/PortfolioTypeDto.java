package it.aredegalli.wallet.dto.portfolio;

import it.aredegalli.wallet.enums.portfolio.PortfolioTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortfolioTypeDto {

    private PortfolioTypeEnum code;
    private String description;

}
