package java16.chatwhatsap.controller;

import java16.chatwhatsap.models.Group;
import java16.chatwhatsap.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.List;
@Controller
public class GroupController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private GroupService groupService;

    // Группа түзүү
    @MessageMapping("/createGroup")
    public void createGroup(String groupName) {
        try {
            Group group = groupService.createGroup(groupName);
            // Топ түзүлдү деген билдирүүнү жөнөтүү
            messagingTemplate.convertAndSend("/topic/group", group);
        } catch (Exception e) {
            // Ката жөнүндө билдирүү
            messagingTemplate.convertAndSend("/topic/error", "Group creation failed: " + e.getMessage());
        }
    }

    // Группага колдонуучуну кошуу
    @MessageMapping("/addUserToGroup")
    public void addUserToGroup(String groupName, String username) {
        Group group = groupService.addUserToGroup(groupName, username);
        String message = username + " has been added to the group.";
        messagingTemplate.convertAndSend("/topic/group/" + groupName, message);
    }

    // Бардык топторду көрүү
    @MessageMapping("/getAllGroups")
    public void getAllGroups() {
        List<Group> allGroups = groupService.getAllGroups();  // Бардык топторду алабыз
        messagingTemplate.convertAndSend("/topic/groups", allGroups); // WebSocket аркылуу жөнөтөбүз
    }
}



