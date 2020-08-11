package com.dam2.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam2.modelo.Usuario;
import com.dam2.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements IUsuarioServicio {

	@Autowired
	UsuarioRepositorio repositorio;

	public boolean insertarUsuario(Usuario usuario) {
		boolean exito = false;

		if (!repositorio.existsById(usuario.getDni())) {
			repositorio.save(usuario);
			exito = true;
		}
		return exito;

	}

	public boolean updateUsuario(Usuario usuario) {

		boolean exito = false;

		if (repositorio.existsById(usuario.getDni())) {
			repositorio.save(usuario);
			exito = true;

		}
		return exito;
	}

	@Override
	public boolean borrarUsuario(String dni) {

		boolean exito = false;
		Optional<Usuario> usuario = repositorio.findById(dni);

		if (usuario.isPresent()) {
			repositorio.deleteById(dni);
			exito = true;

		}

		return exito;
	}

	public Optional<Usuario> consultarUsuario(String dni) {

		Optional<Usuario> optional = Optional.empty();

		if (repositorio.existsById(dni)) {
			optional = repositorio.findById(dni);
		}
		return optional;
	}

	public Optional<Usuario> login(String dni, String contrasenia) {

		Optional<Usuario> optional = Optional.empty();

		if (repositorio.existsById(dni)) {
			optional = repositorio.findByDniAndContrasenia(dni, contrasenia);
		}

		// System.out.println(dni+contrasenia);

		return optional;
	}

}
