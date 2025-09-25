package it.aredegalli.wallet.repository.portfolio;

import it.aredegalli.wallet.entity.portfolio.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PortfolioRepository extends JpaRepository<Portfolio, UUID> {

    List<Portfolio> findByUserIdOrderByCreationDateDesc(UUID userId);

}

