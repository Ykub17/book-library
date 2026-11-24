package mate.academy.booklibrary.repository;

import mate.academy.booklibrary.model.Role;
import mate.academy.booklibrary.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
