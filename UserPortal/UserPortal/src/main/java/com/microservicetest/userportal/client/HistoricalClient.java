package com.microservicetest.userportal.client;

import com.microservicetest.userportal.websocket.WebSocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Component
public class HistoricalClient {

    @Value( "${historicalservice.serviceurl}" )
    private String serviceUrl;

    @Value( "${historicalservice.periodurl}" )
    private String periodUrl;

    private WebSocketController webSocketController;
    WebClient client;

    public void setWebSocketController(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }

    @PostConstruct
    public void initWebclient() {
        client = WebClient.create(serviceUrl);
    }


    public void getHistoricalPeriod(Principal user, Map<String, String> request) {
        client
                .post()
                .uri(periodUrl)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(List.class)
                .subscribe(list -> {
                    webSocketController.sendSpecificResponce(user, list);
                });
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public void setClient(WebClient client) {
        this.client = client;
    }
}
