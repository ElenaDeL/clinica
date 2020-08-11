package com.dam2.app;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dam2.modelo.Usuario;

import daw.com.Teclado;

public class App {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cargarDatos();
		registrarseCon2Paramentros();
	}
	
	private static Optional<Usuario> consultarUsuario2(String dni, String contrasenia) {
		// TODO Auto-generated method stub
		final String URL = "http://192.168.1.42:3306/proyecto/usuarios/consultarbis?dni={dni}&contrasenia={contrasenia}";

		// Cargar parámetros
		Map<String, String> params = new HashMap<>();

		params.put("dni", dni);
		params.put("contrasenia", contrasenia);

		Usuario respuesta;
		Optional<Usuario> optional = Optional.empty();

		RestTemplate restTemplate = new RestTemplate();

		try {

			respuesta = restTemplate.getForObject(URL, Usuario.class, params);
			optional = Optional.of(respuesta);

			System.out.println("usuario : " + respuesta);

		} catch (HttpClientErrorException e) {
			System.out.println("error, no se ha podido realizar el prestamo");
		}
		return optional;

	}
	public static void cargarDatos() {
		// TODO Auto-generated method stub

		final String URL = "http://192.168.1.42:3306/proyecto/usuarios/cargar";

		RestTemplate template = new RestTemplate();

		ResponseEntity<String> respuesta = template.getForEntity(URL,null, String.class);

		Stream<String> stream = Stream.of(respuesta.getBody());
		stream.forEach(System.out::println);
	}

	
	private static void registrarseCon2Paramentros() {
		// TODO Auto-generated method stub

		String dni = "dni";
		String contrasenia ="contrasenia";

		Optional<Usuario> optional = consultarUsuario2(dni, contrasenia);

		if (optional.isEmpty()) {
			insertarUsuario(dni, contrasenia);
		} else {

			System.out.println(optional.get());
		}

	}
	
	public static void insertarUsuario(String dni, String contrasenia) {
		final String URL = "http://localhost:3306/proyecto/usuarios/insertar";

		Usuario respuesta;
		Usuario usuario = new Usuario();
		
		usuario.setDni(dni);
		usuario.setContrasenia(contrasenia);

		RestTemplate restTemplate = new RestTemplate();

		try {
			respuesta = restTemplate.postForObject(URL, usuario, Usuario.class);
			System.out.println(respuesta);

		} catch (HttpClientErrorException e) {
			System.out.println("error " + e.getMessage());
		}

	}

	
	
	
	

}
