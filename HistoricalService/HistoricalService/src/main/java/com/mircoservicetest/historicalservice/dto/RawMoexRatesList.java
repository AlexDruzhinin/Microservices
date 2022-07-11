package com.mircoservicetest.historicalservice.dto;

import java.util.List;

public class RawMoexRatesList {
    List<RawMoexRate> currenciesList;

    public List<RawMoexRate> getCurrenciesList() {
        return currenciesList;
    }

    public void setCurrenciesList(List<RawMoexRate> currenciesList) {
        this.currenciesList = currenciesList;
    }
}
