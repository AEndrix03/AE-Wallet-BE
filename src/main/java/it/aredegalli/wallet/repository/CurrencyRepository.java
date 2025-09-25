package it.aredegalli.wallet.repository;

import it.aredegalli.wallet.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}

