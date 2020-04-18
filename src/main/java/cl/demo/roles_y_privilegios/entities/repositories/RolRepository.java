package cl.demo.roles_y_privilegios.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.demo.roles_y_privilegios.entities.Role;

@Repository
public interface RolRepository extends JpaRepository<Role, Integer> {

}
