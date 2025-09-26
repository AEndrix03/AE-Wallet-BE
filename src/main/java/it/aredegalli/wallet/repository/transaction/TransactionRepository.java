package it.aredegalli.wallet.repository.transaction;

import it.aredegalli.wallet.entity.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>,
        JpaSpecificationExecutor<Transaction> {

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.portfolio.id = :portfolioId")
    BigDecimal sumAmountsByPortfolioId(@Param("portfolioId") UUID portfolioId);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.portfolio.userId = :userId")
    BigDecimal sumAmountsByUserId(@Param("portfolioId") UUID portfolioId);

    Page<Transaction> findByPortfolioIdOrderByCreationDateDesc(
            UUID portfolioId, Pageable pageable);

}

