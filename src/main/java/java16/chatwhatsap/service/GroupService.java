package java16.chatwhatsap.service;

import java16.chatwhatsap.models.Group;
import java16.chatwhatsap.models.User;
import java16.chatwhatsap.repo.GroupRepository;
import java16.chatwhatsap.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    // Уникалдуу топ атын текшерүү
    private boolean isGroupNameUnique(String groupName) {
        return groupRepository.findByName(groupName) == null;
    }

    // Уникалдуу топ атын түзүү
    private String generateUniqueGroupName(String baseName) {
        String newName = baseName;
        int count = 1;
        // Эгер топ аты базада бар болсо, аны өзгөртүп текшерүү
        while (groupRepository.existsByName(newName)) {
            newName = baseName + " (" + count++ + ")";
        }
        return newName;
    }


    // Группа түзүү
    public Group createGroup(String groupName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();  // Колдонуучунун аты алынат

        // Группа атын текшерүү
        if (groupRepository.findByName(groupName) != null) {
            throw new IllegalArgumentException("Group name already exists!");
        }

        Group group = new Group(groupName);

        // Жаңы топту сактоо
        group = groupRepository.save(group);

        return group;
    }

    public Group addUserToGroup(String groupName, String username) {
        Group group = groupRepository.findByName(groupName);
        User user = userRepository.findByUsername(username);

        if (group != null && user != null) {
            group.getUsers().add(user);
            groupRepository.save(group);
        }

        return group;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll(); // Бардык топторду база аркылуу алабыз
    }
}
