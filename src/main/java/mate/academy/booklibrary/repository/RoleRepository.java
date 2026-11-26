package mate.academy.booklibrary.repository;

import java.util.Optional;
import mate.academy.booklibrary.model.Role;
import mate.academy.booklibrary.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
