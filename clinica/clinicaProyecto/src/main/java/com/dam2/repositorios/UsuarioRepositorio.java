package com.dam2.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dam2.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, String>{
	Optional<Usuario> findByDniAndContrasenia(String dni,String contrasenia);
	Optional<Usuario> findByNombre(String nombre);

}
