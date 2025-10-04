package it.aredegalli.wallet.repository.transaction;

import it.aredegalli.wallet.entity.transaction.Transaction;
import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
import it.aredegalli.wallet.service.analytics.dto.PortfolioTypeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>,
        JpaSpecificationExecutor<Transaction> {

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.portfolio.id = :portfolioId")
    BigDecimal sumAmountsByPortfolioId(@Param("portfolioId") UUID portfolioId);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.portfolio.userId = :userId")
    BigDecimal sumAmountsByUserId(@Param("userId") UUID userId);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.portfolio.userId = :userId " +
            "AND t.type = :type " +
            "AND t.creationDate >= :fromDate " +
            "AND t.creationDate <= :toDate")
    BigDecimal sumAmountsByUserIdAndTypeAndDate(
            @Param("userId") UUID userId,
            @Param("type") TransactionTypeEnum type,
            @Param("fromDate") Instant fromDate,
            @Param("toDate") Instant toDate
    );

    @Query("SELECT t.portfolio.type as portfolioType, " +
            "SUM(t.amount) as totalAmount " +
            "FROM Transaction t " +
            "WHERE t.portfolio.userId = :userId " +
            "GROUP BY t.portfolio.type")
    List<PortfolioTypeSummary> sumAmountsByUserIdAndPortfolioType(@Param("userId") UUID userId);

    Page<Transaction> findByPortfolioIdOrderByCreationDateDesc(
            UUID portfolioId, Pageable pageable);

}

