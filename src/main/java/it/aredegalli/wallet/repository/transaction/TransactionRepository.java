package it.aredegalli.wallet.repository.transaction;

import it.aredegalli.wallet.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

