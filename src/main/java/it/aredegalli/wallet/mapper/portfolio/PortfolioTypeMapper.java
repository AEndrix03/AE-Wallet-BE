package it.aredegalli.wallet.mapper.portfolio;

import it.aredegalli.wallet.dto.portfolio.PortfolioTypeDto;
import it.aredegalli.wallet.entity.Currency;
import it.aredegalli.wallet.entity.portfolio.PortfolioType;
import it.aredegalli.wallet.mapper.base.IDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class PortfolioTypeMapper implements IDtoMapper<PortfolioTypeDto, PortfolioType> {

    public PortfolioTypeDto toDto(PortfolioType entity) {
        return new PortfolioTypeDto(entity.getCode(), entity.getName());
    }

}
