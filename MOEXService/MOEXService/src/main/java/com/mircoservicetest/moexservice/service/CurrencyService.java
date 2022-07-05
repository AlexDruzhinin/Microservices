package com.mircoservicetest.moexservice.service;

import com.mircoservicetest.moexservice.client.CurrencyClient;
import com.mircoservicetest.moexservice.dto.CurrenciesDTO;
import com.mircoservicetest.moexservice.dto.CurrencyPairsDTO;
import com.mircoservicetest.moexservice.parser.Parser;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final CurrencyClient currencyClient;
    private final Parser parser;

    public CurrencyService(CurrencyClient currencyClient, Parser parser) {
        this.currencyClient = currencyClient;
        this.parser = parser;
    }

    CurrenciesDTO getCurrenciesFromMOEX(CurrencyPairsDTO currencyPairsDTO) {

    }
}
