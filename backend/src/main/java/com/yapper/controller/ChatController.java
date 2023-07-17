package com.yapper.controller;

import com.yapper.message.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/channel/id")
    public Message chat(Message message) throws InterruptedException {
        Thread.sleep(1000);
        return new Message("Hello, " + HtmlUtils.htmlEscape(message.getContent()));
    }
}
