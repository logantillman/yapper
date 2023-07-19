package com.yapper.controller;

import com.yapper.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Slf4j
@Controller
public class ChatController {

    @MessageMapping("/{serverId}") // Wildcard routing. We want all sent messages to be handled the same
    @SendTo("/topic/{serverId}") // Unique for server??
    public Message chat(@DestinationVariable String serverId, Message message) throws InterruptedException {
        return new Message(HtmlUtils.htmlEscape(message.getContent()));
    }

}
