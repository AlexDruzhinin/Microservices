package com.mircoservicetest.moexservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "currencyMOEXClient", url = "${moex.currency.url}")
public interface CurrencyClient {
    @GetMapping
    String getCurrenciesFromMOEX();
}
