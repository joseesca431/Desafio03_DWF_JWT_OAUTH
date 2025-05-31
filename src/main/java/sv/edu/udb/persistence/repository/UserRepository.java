package sv.edu.udb.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.persistence.domain.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
