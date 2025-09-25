package it.aredegalli.wallet.dto.portfolio;

import it.aredegalli.wallet.enums.portfolio.PortfolioStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortfolioStatusDto {

    private PortfolioStatusEnum code;
    private String description;

}
