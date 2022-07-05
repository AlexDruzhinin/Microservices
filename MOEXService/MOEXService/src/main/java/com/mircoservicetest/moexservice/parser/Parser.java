package com.mircoservicetest.moexservice.parser;

import com.mircoservicetest.moexservice.dto.CurrencyPairsDTO;

public interface Parser {
    List<Currency> parse(String currenciesAsString);
}
