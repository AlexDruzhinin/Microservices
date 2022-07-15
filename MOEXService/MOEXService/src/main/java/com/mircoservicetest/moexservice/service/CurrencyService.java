package com.mircoservicetest.moexservice.service;

import com.mircoservicetest.moexservice.client.CurrencyClient;
import com.mircoservicetest.moexservice.dto.CurrenciesDTO;
import com.mircoservicetest.moexservice.dto.CurrencyPairsDTO;
import com.mircoservicetest.moexservice.model.Currency;
import com.mircoservicetest.moexservice.parser.Parser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private final CurrencyClient currencyClient;
    private final Parser parser;

    @Value("${app.generateData}")
    private boolean generateData;

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

        if (generateData) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat numberFormat = new DecimalFormat("#.0000", symbols);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            resultCurrencies.stream().forEach(c -> {
                c.setPrice(numberFormat.format(Double.parseDouble(c.getPrice()) * (0.9 + 0.2 * Math.random()))); //adding random +-10%
                c.setTradedate(LocalDate.now().toString());
                c.setTradetime(LocalTime.now().format(dateTimeFormatter));
            });
        }

        CurrenciesDTO result = new CurrenciesDTO();
        result.setCurrenciesList(resultCurrencies);
        return result;
    }
}
