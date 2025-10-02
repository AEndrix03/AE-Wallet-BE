package it.aredegalli.wallet.service.transaction.finder.specification;

import it.aredegalli.wallet.entity.transaction.Transaction;
import it.aredegalli.wallet.enums.CurrencyEnum;
import it.aredegalli.wallet.enums.transaction.TransactionTypeEnum;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionSpecifications {

    public static Specification<Transaction> belongsToUser(UUID userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) return criteriaBuilder.conjunction();

            Join<Object, Object> portfolio = root.join("portfolio", JoinType.INNER);
            return criteriaBuilder.equal(portfolio.get("userId"), userId);
        };
    }

    public static Specification<Transaction> hasAmountGreaterThan(Double amount) {
        return (root, query, criteriaBuilder) -> {
            if (amount == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), amount);
        };
    }

    public static Specification<Transaction> hasCurrency(CurrencyEnum currency) {
        return (root, query, criteriaBuilder) -> {
            if (currency == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("currency"), currency);
        };
    }

    public static Specification<Transaction> hasType(TransactionTypeEnum type) {
        return (root, query, criteriaBuilder) -> {
            if (type == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<Transaction> createdBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return (root, query, criteriaBuilder) -> {
            if (dateFrom == null && dateTo == null) {
                return criteriaBuilder.conjunction();
            }
            if (dateFrom == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), dateTo);
            }
            if (dateTo == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), dateFrom);
            }
            return criteriaBuilder.between(root.get("creationDate"), dateFrom, dateTo);
        };
    }
}