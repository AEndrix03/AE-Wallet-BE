package it.aredegalli.wallet.service.portfolio;

import it.aredegalli.wallet.dto.portfolio.PortfolioDto;
import it.aredegalli.wallet.dto.portfolio.PortfolioStatusDto;
import it.aredegalli.wallet.dto.portfolio.PortfolioTypeDto;
import it.aredegalli.wallet.dto.portfolio.update.PortfolioSaveDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PortfolioService {
    List<PortfolioTypeDto> getAllPortfolioTypes();

    List<PortfolioStatusDto> getAllPortfolioStatuses();

    List<PortfolioDto> getAllUserPortfolios(UUID userId);

    Optional<PortfolioDto> findPortfolioById(UUID portfolioId);

    UUID savePortfolio(PortfolioSaveDto saveDto);

    UUID deletePortfolio(UUID portfolioId);
}
