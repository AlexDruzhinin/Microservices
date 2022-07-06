package com.mircoservicetest.moexservice.parser;

import com.mircoservicetest.moexservice.model.Currency;

import java.util.List;

public interface Parser {
    List<Currency> parse(String currenciesAsString);
}
