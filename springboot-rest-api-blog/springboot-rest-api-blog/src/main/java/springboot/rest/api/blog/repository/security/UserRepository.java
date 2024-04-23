package springboot.rest.api.blog.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rest.api.blog.entity.security.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameOrEmail(String userName, String email);

    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);
}
