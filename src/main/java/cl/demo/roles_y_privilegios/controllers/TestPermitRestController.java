package cl.demo.roles_y_privilegios.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.demo.roles_y_privilegios.utils.ResultadoProc;

@RestController
@RequestMapping("/api")
public class TestPermitRestController {

	@PreAuthorize("hasAuthority('CREAR_USUARIO')")
	@GetMapping("/usuario/nuevo")
	public ResponseEntity<ResultadoProc<String>> saveUsuario() {
		ResultadoProc<String> salida = new ResultadoProc<>();
		salida.setResultado("Usuario creado");
		return new ResponseEntity<ResultadoProc<String>>(salida, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('EDITAR_USUARIO')")
	@GetMapping("/usuario/editar")
	public ResponseEntity<ResultadoProc<String>> editarUsuario() {
		ResultadoProc<String> salida = new ResultadoProc<>();
		salida.setResultado("Usuario editado");
		return new ResponseEntity<ResultadoProc<String>>(salida, HttpStatus.OK);
	}

}
