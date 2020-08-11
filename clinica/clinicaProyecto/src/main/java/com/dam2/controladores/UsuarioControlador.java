package com.dam2.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dam2.modelo.Usuario;
import com.dam2.servicios.IUsuarioServicio;

@Controller
@RequestMapping("proyecto/usuarios")
public class UsuarioControlador {

	@Autowired
	IUsuarioServicio servicioUsuario;

	@GetMapping("/cargar")
	public ResponseEntity<String> cargar() {

		ResponseEntity<String> response;

		System.out.println("gvhgdsghsjeghhighi´<Eswo");
		Usuario u1 = Usuario.builder().nombre("001").contrasenia("qaz").build();
		Usuario u2 = Usuario.builder().nombre("003").contrasenia("wsx").build();

		servicioUsuario.insertarUsuario(u1);
		servicioUsuario.insertarUsuario(u2);

		response = new ResponseEntity<>("carga realizada correctamente", HttpStatus.OK);
		return response;
	}

	@GetMapping("/consultarbis")
	public ResponseEntity<Usuario> consultarUsuario2(@RequestParam String dni, @RequestParam String contrasenia) {

		ResponseEntity<Usuario> respuesta;

		System.out.println(dni + contrasenia);
		Optional<Usuario> usuario = servicioUsuario.login(dni, contrasenia);

		// true si tiene un objeto sino null
		if (usuario.isPresent()) {
			respuesta = new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
		} else {
			respuesta = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		return respuesta;
	}

	@PostMapping("/insertar")
	public ResponseEntity<Usuario> insertarUsuario(@RequestBody Usuario usuario) {
		ResponseEntity<Usuario> respuesta;

		if (servicioUsuario.insertarUsuario(usuario)) {
			respuesta = new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
			System.out.println("inserta " + usuario.toString());

		} else {
			respuesta = new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);

		}

		return respuesta;
	}

}
