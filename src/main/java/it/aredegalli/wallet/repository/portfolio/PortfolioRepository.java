package it.aredegalli.wallet.repository.portfolio;

import it.aredegalli.wallet.entity.portfolio.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}

