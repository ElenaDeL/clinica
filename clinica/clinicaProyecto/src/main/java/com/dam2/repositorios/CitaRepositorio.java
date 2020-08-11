package com.dam2.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dam2.modelo.Cita;

@Repository
public interface CitaRepositorio extends CrudRepository<Cita, String>{

}
