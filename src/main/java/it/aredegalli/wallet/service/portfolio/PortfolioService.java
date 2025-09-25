package it.aredegalli.wallet.service.portfolio;

import it.aredegalli.wallet.dto.portfolio.PortfolioStatusDto;
import it.aredegalli.wallet.dto.portfolio.PortfolioTypeDto;

import java.util.List;

public interface PortfolioService {
    List<PortfolioTypeDto> getAllPortfolioTypes();

    List<PortfolioStatusDto> getAllPortfolioStatuses();
}
