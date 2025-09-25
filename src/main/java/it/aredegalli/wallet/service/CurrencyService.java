package it.aredegalli.wallet.service;

import it.aredegalli.wallet.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAllCurrencies();
}
