package cl.demo.roles_y_privilegios.entities.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.demo.roles_y_privilegios.entities.Privilegio;
import cl.demo.roles_y_privilegios.entities.Role;
import cl.demo.roles_y_privilegios.entities.Usuario;
import cl.demo.roles_y_privilegios.entities.repositories.UsuarioRepository;
import cl.demo.roles_y_privilegios.entities.services.IUsuarioService;
import cl.demo.roles_y_privilegios.utils.ResultadoProc;
import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class UsuarioService implements IUsuarioService, UserDetailsService {
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String emailOrUser) throws UsernameNotFoundException {
		emailOrUser = emailOrUser.toLowerCase().trim();
		Usuario usuario = usuarioRepository.findByEmail(emailOrUser);
		if (usuario == null) {
			log.info("Usuario ingresado no existe");
			throw new UsernameNotFoundException("Usuario o Clave incorrectos");
		}
//		List<GrantedAuthority> grantedAuths = new ArrayList<>();
//		for (Role rol : usuario.getRoles()) {
//			grantedAuths.add(new SimpleGrantedAuthority(rol.getNombre()));
//		}
		return new User(emailOrUser, usuario.getPassword(), true, true, true, true, getAuthorities(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilegio> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivilegios());
		}
		for (Privilegio item : collection) {
			privileges.add(item.getDescripcion());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	@Override
	public ResultadoProc<Usuario> findByEmail(String email) {
		ResultadoProc<Usuario> salida = new ResultadoProc<Usuario>();
		try {
			Usuario usuario = usuarioRepository.findByEmail(email);
			salida.setResultado(usuario);
			salida.setMensaje("Usuario encontrado correctamente");
			if (usuario == null) {
				salida.setError(true);
				salida.setMensaje("No se ha encontrado el usuario con el email: " + email);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.setError(true);
			salida.setMensaje("Se produjo un error inesperado al intentar obtener el usuario");
		}
		return salida;
	}

	@Override
	public ResultadoProc<Usuario> findById(int usuarioId) {
		ResultadoProc<Usuario> salida = new ResultadoProc<Usuario>();
		try {
			Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
			if (usuario == null) {
				salida.setError(true);
				salida.setMensaje("No se ha encontrado el usuario con el ID " + usuarioId);
			}
			salida.setResultado(usuario);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.setMensaje("Se produjo un error inesperado al intentar obtener el usuario con el ID " + usuarioId);
		}
		return salida;
	}

	@Override
	public ResultadoProc<List<Usuario>> findAll() {
		ResultadoProc<List<Usuario>> salida = new ResultadoProc<List<Usuario>>();
		try {
			List<Usuario> usuarios = usuarioRepository.findAll();
			salida.setResultado(usuarios);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida.setMensaje("Se produjo un error inesperado al intentar listar los usuarios");
		}
		return salida;
	}

}
