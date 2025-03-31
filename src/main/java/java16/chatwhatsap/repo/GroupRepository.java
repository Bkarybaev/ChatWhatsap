package java16.chatwhatsap.repo;

import java16.chatwhatsap.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);

    boolean existsByName(String newName);
}
