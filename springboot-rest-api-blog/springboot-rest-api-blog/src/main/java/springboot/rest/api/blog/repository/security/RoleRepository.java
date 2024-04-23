package springboot.rest.api.blog.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rest.api.blog.entity.security.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
