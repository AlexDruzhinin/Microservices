package com.mircoservicetest.historicalservice.controller;

import com.mircoservicetest.historicalservice.dao.RateRepository;
import com.mircoservicetest.historicalservice.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rate")
public class RatesController {
    private final RateRepository rateRepository;

    RatesController(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @PostMapping("getLast")
    public Rate getLastValue(@RequestBody String currencyName) {
        //System.out.println("Check controller. Line: " + currencyName);
        //System.out.println("Check controller. Object: " + rateRepository.getLastRate("currencyName").toString());
        List<Rate> rateList= rateRepository.findFirstByNameOrderByTradeTimeDesc(currencyName);
        if (rateList != null && rateList.size() > 0) {
            return rateList.get(0);
        }
        return null;
    }

    @PostMapping("getPeriod")
    public List<Rate>  getPeriod (@RequestBody Map<String, String> requestData) {
        String currencyName = requestData.get("currencyName");
        String startTimestampString = requestData.get("startTimestampString");
        String stopTimestampString = requestData.get("stopTimestampString");
        if (currencyName == null || startTimestampString == null || stopTimestampString == null) {
            return null; //todo add exception
        }
        LocalDateTime startTimestamp = LocalDateTime.parse(startTimestampString);
        LocalDateTime stopTimestamp = LocalDateTime.parse(stopTimestampString);
        List<Rate> rateList= rateRepository.findByNameAndTradeTimeBetween(currencyName, startTimestamp, stopTimestamp);
        if (rateList != null) {
            return rateList;
        }
        return null;
    }//, String )

}
