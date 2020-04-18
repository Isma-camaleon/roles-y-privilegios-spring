package cl.demo.roles_y_privilegios.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.demo.roles_y_privilegios.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	/**
	 * 
	 * @param emailOrUser (Email del usuario)
	 * @return El usuario que coincida con el email
	 */
	@Query("select u from Usuario u where u.email = ?1")
	Usuario findByEmail(String email);

}
