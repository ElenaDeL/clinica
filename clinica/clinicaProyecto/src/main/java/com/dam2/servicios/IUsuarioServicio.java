package com.dam2.servicios;

import java.util.Optional;

import com.dam2.modelo.Usuario;

public interface IUsuarioServicio {

	boolean borrarUsuario(String dni);

	boolean insertarUsuario(Usuario usuario);

	boolean updateUsuario(Usuario usuario);

	Optional<Usuario> consultarUsuario(String dni);

	Optional<Usuario> login(String dni, String contrasenia);
}
