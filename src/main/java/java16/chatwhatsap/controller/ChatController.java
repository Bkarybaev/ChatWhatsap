package java16.chatwhatsap.controller;

import java16.chatwhatsap.models.Message;
import java16.chatwhatsap.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(Message message) {
        return chatService.saveMessage(message.getSender(), message.getContent());
    }
}


