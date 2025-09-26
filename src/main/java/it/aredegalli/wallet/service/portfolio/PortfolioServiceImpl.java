package it.aredegalli.wallet.service.portfolio;

import it.aredegalli.wallet.dto.portfolio.PortfolioDto;
import it.aredegalli.wallet.dto.portfolio.PortfolioStatusDto;
import it.aredegalli.wallet.dto.portfolio.PortfolioTypeDto;
import it.aredegalli.wallet.dto.portfolio.update.PortfolioSaveDto;
import it.aredegalli.wallet.mapper.portfolio.PortfolioMapper;
import it.aredegalli.wallet.mapper.portfolio.PortfolioStatusMapper;
import it.aredegalli.wallet.mapper.portfolio.PortfolioTypeMapper;
import it.aredegalli.wallet.repository.portfolio.PortfolioRepository;
import it.aredegalli.wallet.repository.portfolio.PortfolioStatusRepository;
import it.aredegalli.wallet.repository.portfolio.PortfolioTypeRepository;
import it.aredegalli.wallet.repository.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioTypeRepository portfolioTypeRepository;
    private final PortfolioStatusRepository portfolioStatusRepository;
    private final TransactionRepository transactionRepository;

    private final PortfolioMapper portfolioMapper;
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

    @Override
    public List<PortfolioDto> getAllUserPortfolios(UUID userId) {
        return this.portfolioRepository.findByUserIdOrderByCreationDateDesc(userId).stream()
                .map(p -> {
                    PortfolioDto dto = portfolioMapper.toDto(p);
                    dto.setBalance(this.transactionRepository.sumAmountsByPortfolioId(p.getId()));
                    return dto;
                })
                .toList();
    }

    @Override
    public Optional<PortfolioDto> findPortfolioById(UUID portfolioId) {
        return this.portfolioRepository.findById(portfolioId)
                .map(portfolioMapper::toDto);
    }

    @Override
    public UUID savePortfolio(PortfolioSaveDto saveDto) {
        var portfolio = this.portfolioMapper.toEntity(saveDto);
        portfolio.setUserId(saveDto.getUserId());
        return this.portfolioRepository.save(portfolio).getId();
    }

    @Override
    public UUID deletePortfolio(UUID portfolioId) {
        this.portfolioRepository.deleteById(portfolioId);
        return portfolioId;
    }

}
