package it.aredegalli.wallet.repository.portfolio;

import it.aredegalli.wallet.entity.portfolio.PortfolioStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioStatusRepository extends JpaRepository<PortfolioStatus, String> {
}

