package java16.chatwhatsap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class VideoCallController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Видео чалууну баштоо
    @MessageMapping("/startVideoCall")
    public void startVideoCall(String targetUser) {
        // Видео чалуу башталганын билдирүү
        messagingTemplate.convertAndSend("/topic/videoCall/" + targetUser, "start");
    }

    // Видео чалууга жооп берүү
    @MessageMapping("/answerVideoCall")
    public void answerVideoCall(String targetUser) {
        // Видео чалууга жооп бергенин билдирүү
        messagingTemplate.convertAndSend("/topic/videoCall/" + targetUser, "answer");
    }
}
