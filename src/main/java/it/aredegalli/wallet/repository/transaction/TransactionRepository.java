package it.aredegalli.wallet.repository.transaction;

import it.aredegalli.wallet.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.portfolio.id = :portfolioId")
    BigDecimal sumAmountsByPortfolioId(@Param("portfolioId") UUID portfolioId);

}

