package com.mircoservicetest.historicalservice.scheduledTasks;

import com.mircoservicetest.historicalservice.dao.RateRepository;
import com.mircoservicetest.historicalservice.dto.CurrencyPairsDTO;
import com.mircoservicetest.historicalservice.dto.RawMoexRate;
import com.mircoservicetest.historicalservice.dto.RawMoexRatesList;
import com.mircoservicetest.historicalservice.model.Rate;
import com.mircoservicetest.historicalservice.util.RawMoexToRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "moexservice")
public class MoexPinger {

    private final RestTemplate restTemplate;
    private RateRepository rateRepository;
    private List<String> pairs;
    private String url;

    @Autowired
    public MoexPinger(RateRepository rateRepository, RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        this.rateRepository = rateRepository;
    }

    @Scheduled(fixedDelayString = "${moexservice.pinginterval}")
    public void pingMoexService() {

        CurrencyPairsDTO currencyPairsDTO = new CurrencyPairsDTO(); //todo consider autowired
        currencyPairsDTO.setPairs(pairs);
        ResponseEntity<RawMoexRatesList> rateList = restTemplate.postForEntity(url, currencyPairsDTO, RawMoexRatesList.class);
        //todo should we store all currencies in one table?
        for (RawMoexRate rawMoexRate : rateList.getBody().getCurrenciesList()) {
            Rate rate = RawMoexToRate.convertToRate(rawMoexRate);
            rateRepository.save(rate);
        }
    }


    public List<String> getPairs() {
        return pairs;
    }

    public void setPairs(List<String> pairs) {
        this.pairs = pairs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
