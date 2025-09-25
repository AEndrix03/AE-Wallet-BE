package it.aredegalli.wallet.repository.portfolio;

import it.aredegalli.wallet.entity.portfolio.PortfolioType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioTypeRepository extends JpaRepository<PortfolioType, String> {
}

