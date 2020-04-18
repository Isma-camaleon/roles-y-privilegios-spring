package cl.demo.roles_y_privilegios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.demo.roles_y_privilegios.entities.Usuario;
import cl.demo.roles_y_privilegios.entities.services.IUsuarioService;
import cl.demo.roles_y_privilegios.utils.ResultadoProc;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
	@Autowired
	IUsuarioService usuarioService;

	@GetMapping("/{id}")
	public ResponseEntity<ResultadoProc<Usuario>> findById(@PathVariable("id") int usuarioId) {
		ResultadoProc<Usuario> salida = usuarioService.findById(usuarioId);
		return new ResponseEntity<ResultadoProc<Usuario>>(salida, HttpStatus.OK);
	}

	@GetMapping("/find-all")
	public ResponseEntity<ResultadoProc<List<Usuario>>> findAll() {
		ResultadoProc<List<Usuario>> salida = usuarioService.findAll();
		return new ResponseEntity<ResultadoProc<List<Usuario>>>(salida, HttpStatus.OK);
	}
}
