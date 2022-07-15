package com.microservicetest.telegrambot.bot.service;

import com.microservicetest.telegrambot.bot.model.Rate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
public class HistoricalServiceClient {

    @Value( "${historicalservice.serviceurl}" )
    private String serviceUrl;

    @Value( "${historicalservice.rateurl}" )
    private String rateurl;

    WebClient client;

    @PostConstruct
    public void initWebclient() {
        client = WebClient.create(serviceUrl);
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public void setClient(WebClient client) {
        this.client = client;
    }

    public Mono<Rate> getLastRateValueFromHistoricalService(String currency) {
        Mono<Rate> mono = client
                .post()
                .uri(rateurl)
                .bodyValue(currency)
                .retrieve()
                .bodyToMono(Rate.class)
                ;
        return mono;
    }
}
