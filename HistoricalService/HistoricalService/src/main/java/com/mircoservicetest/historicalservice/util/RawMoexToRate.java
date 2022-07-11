package com.mircoservicetest.historicalservice.util;

import com.mircoservicetest.historicalservice.dto.RawMoexRate;
import com.mircoservicetest.historicalservice.model.Rate;

import java.time.LocalDateTime;

public class RawMoexToRate {
    public static Rate convertToRate(RawMoexRate rawMoexRate) {
        Rate rate = new Rate();
        rate.setName(RateNameMapper.mapMoexToName(rawMoexRate.getSecid()));
        rate.setPrice(Double.parseDouble(rawMoexRate.getPrice()));
        rate.setTradeTime(LocalDateTime.parse(rawMoexRate.getTradedate() + "T" + rawMoexRate.getTradetime()));
        return rate;
    }


    class RateNameMapper {
        public static String mapMoexToName(String moexName) {
            if (moexName.equals("USD000UTSTOM")) {
                return "USD";
            }
            if (moexName.equals("EUR_RUB__TOM")) {
                return "EUR";
            }
            if (moexName.equals("CNYRUB_TOM")) {
                return "CNY";
            }
            return "MAPPER ERROR";
        }
    }

}
