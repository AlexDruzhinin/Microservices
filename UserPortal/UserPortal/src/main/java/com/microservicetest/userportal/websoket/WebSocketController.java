package com.microservicetest.userportal.websoket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.user.UserDestinationResolver;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserDestinationResolver userDestinationResolver;
    /*@MessageMapping("/rates")
    @SendToUser("/user/queue/specific-user")
    public String processMessageFromClient(@Payload String message, Principal principal) throws Exception {
        System.out.println(message);
        return "";
    }*/

    @MessageMapping("/secured/room")
    public void sendSpecific(
            Message<Map<String, String>> message,
            Principal user,
            @Header("simpSessionId") String sessionId) throws Exception {
        simpMessagingTemplate.convertAndSendToUser(user.getName(), "/secured/user/queue/specific-user", message.getPayload());
    }
}
