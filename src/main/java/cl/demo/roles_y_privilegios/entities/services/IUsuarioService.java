package cl.demo.roles_y_privilegios.entities.services;

import java.util.List;

import cl.demo.roles_y_privilegios.entities.Usuario;
import cl.demo.roles_y_privilegios.utils.ResultadoProc;

public interface IUsuarioService {

	ResultadoProc<Usuario> findByEmail(String email);

	ResultadoProc<List<Usuario>> findAll();

	ResultadoProc<Usuario> findById(int usuarioId);

}
