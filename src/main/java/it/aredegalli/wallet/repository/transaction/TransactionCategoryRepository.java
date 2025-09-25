package it.aredegalli.wallet.repository.transaction;

import it.aredegalli.wallet.entity.transaction.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, String> {
}

