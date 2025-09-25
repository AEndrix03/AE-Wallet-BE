package it.aredegalli.wallet.service.portfolio;

import it.aredegalli.wallet.dto.portfolio.PortfolioStatusDto;
import it.aredegalli.wallet.dto.portfolio.PortfolioTypeDto;
import it.aredegalli.wallet.mapper.portfolio.PortfolioStatusMapper;
import it.aredegalli.wallet.mapper.portfolio.PortfolioTypeMapper;
import it.aredegalli.wallet.repository.portfolio.PortfolioRepository;
import it.aredegalli.wallet.repository.portfolio.PortfolioStatusRepository;
import it.aredegalli.wallet.repository.portfolio.PortfolioTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioTypeRepository portfolioTypeRepository;
    private final PortfolioStatusRepository portfolioStatusRepository;

    private final PortfolioTypeMapper portfolioTypeMapper;
    private final PortfolioStatusMapper portfolioStatusMapper;

    @Override
    public List<PortfolioTypeDto> getAllPortfolioTypes() {
        return portfolioTypeRepository.findAll().stream()
                .map(portfolioTypeMapper::toDto)
                .toList();
    }

    @Override
    public List<PortfolioStatusDto> getAllPortfolioStatuses() {
        return portfolioStatusRepository.findAll().stream()
                .map(portfolioStatusMapper::toDto)
                .toList();
    }

}
