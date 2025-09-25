package it.aredegalli.wallet.service;

import it.aredegalli.wallet.dto.CurrencyDto;
import it.aredegalli.wallet.mapper.CurrencyMapper;
import it.aredegalli.wallet.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return this.currencyMapper.toDto(this.currencyRepository.findAll());
    }

}
