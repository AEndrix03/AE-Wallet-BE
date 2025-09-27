package it.aredegalli.wallet.controller;

import it.aredegalli.wallet.dto.portfolio.PortfolioDto;
import it.aredegalli.wallet.dto.portfolio.PortfolioStatusDto;
import it.aredegalli.wallet.dto.portfolio.PortfolioTypeDto;
import it.aredegalli.wallet.dto.portfolio.update.PortfolioSaveDto;
import it.aredegalli.wallet.service.portfolio.PortfolioService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;
    
    @GetMapping("types")
    public List<PortfolioTypeDto> getAllPortfolioTypes() {
        return this.portfolioService.getAllPortfolioTypes();
    }

    @GetMapping("statuses")
    public List<PortfolioStatusDto> getAllPortfolioStatuses() {
        return this.portfolioService.getAllPortfolioStatuses();
    }

    @GetMapping("user")
    public List<PortfolioDto> getAllUserPortfolios(@RequestParam() @NotNull UUID id) {
        return this.portfolioService.getAllUserPortfolios(id);
    }

    @GetMapping()
    public Optional<PortfolioDto> getPortfolioById(@RequestParam() @NotNull UUID id) {
        return this.portfolioService.getPortfolioById(id);
    }

    @PatchMapping()
    public UUID savePortfolio(@RequestBody PortfolioSaveDto save) {
        return this.portfolioService.savePortfolio(save);
    }

    @DeleteMapping()
    public UUID deletePortfolio(@RequestParam() @NotNull UUID id) {
        return this.portfolioService.deletePortfolio(id);
    }

}
