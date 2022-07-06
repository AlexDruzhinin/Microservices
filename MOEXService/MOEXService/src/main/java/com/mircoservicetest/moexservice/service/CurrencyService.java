package com.mircoservicetest.moexservice.service;

import com.mircoservicetest.moexservice.client.CurrencyClient;
import com.mircoservicetest.moexservice.dto.CurrenciesDTO;
import com.mircoservicetest.moexservice.dto.CurrencyPairsDTO;
import com.mircoservicetest.moexservice.model.Currency;
import com.mircoservicetest.moexservice.parser.Parser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private final CurrencyClient currencyClient;
    private final Parser parser;

    public CurrencyService(CurrencyClient currencyClient, Parser parser) {
        this.currencyClient = currencyClient;
        this.parser = parser;
    }

    public CurrenciesDTO getCurrenciesFromMOEX(CurrencyPairsDTO currencyPairsDTO) {
        String xmlFromMoex = currencyClient.getCurrenciesFromMOEX();
        List<Currency> allCurrencies = parser.parse(xmlFromMoex);

        List<Currency> resultCurrencies = allCurrencies.stream()
                .filter(b -> currencyPairsDTO.getPairs().contains(b.getSecid()))
                .collect(Collectors.toList());

        CurrenciesDTO result = new CurrenciesDTO();
        result.setCurrenciesList(resultCurrencies);
        return result;
    }
}
