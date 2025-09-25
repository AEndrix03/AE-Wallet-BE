package it.aredegalli.wallet.mapper.portfolio;

import it.aredegalli.wallet.dto.portfolio.PortfolioStatusDto;
import it.aredegalli.wallet.entity.portfolio.PortfolioStatus;
import it.aredegalli.wallet.mapper.base.IDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class PortfolioStatusMapper implements IDtoMapper<PortfolioStatusDto, PortfolioStatus> {

    public PortfolioStatusDto toDto(PortfolioStatus entity) {
        return new PortfolioStatusDto(entity.getCode(), entity.getName());
    }

}
