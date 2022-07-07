package com.mircoservicetest.historicalservice.scheduledTasks;

import com.mircoservicetest.historicalservice.dao.RateRepository;
import com.mircoservicetest.historicalservice.dto.CurrencyPairsDTO;
import com.mircoservicetest.historicalservice.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
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

        System.out.println(restTemplate.postForEntity(url, currencyPairsDTO, String.class));

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
