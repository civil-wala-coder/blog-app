package reddit.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reddit.blog.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findByName(String roleUser);
}
