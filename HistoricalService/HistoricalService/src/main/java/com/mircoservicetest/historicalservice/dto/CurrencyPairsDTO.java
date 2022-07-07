package com.mircoservicetest.historicalservice.dto;

import java.util.List;

public class CurrencyPairsDTO {
    private List<String> pairs;

    public CurrencyPairsDTO() {
    }

    public List<String> getPairs() {
        return pairs;
    }

    public void setPairs(List<String> pairs) {
        this.pairs = pairs;
    }
}
