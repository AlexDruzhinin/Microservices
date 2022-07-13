package com.microservicetest.userportal.websocket;

import com.microservicetest.userportal.client.HistoricalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.UserDestinationResolver;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class WebSocketController {


    private final SimpMessagingTemplate simpMessagingTemplate;
    private final HistoricalClient historicalClient;

    @Autowired
    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate, HistoricalClient historicalClient) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.historicalClient = historicalClient;
        historicalClient.setWebSocketController(this); //todo this should be refactored to avoid circular ref
    }

    @MessageMapping("/secured/room")
    public void recieveRatesRequestFromFront(
            Message<Map<String, String>> message,
            Principal user) throws Exception {
        historicalClient.getHistoricalPeriod(user, message.getPayload());
    }

    public void sendSpecificResponce(Principal user, List payload) {
        simpMessagingTemplate.convertAndSendToUser(user.getName(), "/secured/user/queue/specific-user", payload);
    }
}
