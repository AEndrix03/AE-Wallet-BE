package it.aredegalli.wallet.repository.transaction;

import it.aredegalli.wallet.entity.transaction.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, String> {
}

