package com.mircoservicetest.moexservice.controller;

import com.mircoservicetest.moexservice.dto.CurrenciesDTO;
import com.mircoservicetest.moexservice.dto.CurrencyPairsDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class MoexCurrencyController {
    private final CurrencyService currencyService;

    @PostMapping("/getCurrency")
    public CurrenciesDTO getCurrenciesFromMOEX(@RequestBody CurrencyPairsDTO currencyPairsDTO) {
        return currencyService.getCurrenciesFromMOEX(currencyPairsDTO);
    }
}
