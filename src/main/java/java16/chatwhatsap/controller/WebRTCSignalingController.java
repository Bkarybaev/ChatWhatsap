package java16.chatwhatsap.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebRTCSignalingController {

    @MessageMapping("/sendOffer")
    @SendTo("/topic/offer")
    public String sendOffer(String offer) {
        return offer; // Видеочалуу үчүн Offer жөнөтүлөт
    }

    @MessageMapping("/sendAnswer")
    @SendTo("/topic/answer")
    public String sendAnswer(String answer) {
        return answer; // Видеочалуу үчүн Answer жөнөтүлөт
    }

    @MessageMapping("/sendCandidate")
    @SendTo("/topic/candidate")
    public String sendCandidate(String candidate) {
        return candidate; // ICE Candidate жөнөтүлөт
    }
}

