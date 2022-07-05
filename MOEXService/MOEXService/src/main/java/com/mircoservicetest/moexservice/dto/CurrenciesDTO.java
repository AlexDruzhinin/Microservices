package com.mircoservicetest.moexservice.dto;

import com.mircoservicetest.moexservice.model.Currency;

import java.util.List;

public class CurrenciesDTO {
    List<Currency> currenciesList;

    public CurrenciesDTO() {
    }

    public List<Currency> getCurrenciesList() {
        return currenciesList;
    }

    public void setCurrenciesList(List<Currency> currenciesList) {
        this.currenciesList = currenciesList;
    }
}
