package java16.chatwhatsap.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender; // Ким жазды
    private String content; // Билдирүү тексти

    private LocalDateTime timestamp; // Убакыт белгиси
}

