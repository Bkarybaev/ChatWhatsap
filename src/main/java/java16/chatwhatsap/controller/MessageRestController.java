package java16.chatwhatsap.controller;

import java16.chatwhatsap.models.Message;
import java16.chatwhatsap.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageRestController {

    private final ChatService chatService;

    @GetMapping
    public List<Message> getMessages() {
        return chatService.getAllMessages();
    }
}

