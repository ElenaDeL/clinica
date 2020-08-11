package com.dam2.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dam2.modelo.Clinica;

@Repository
public interface ClinicaRepositorio extends CrudRepository<Clinica, String>{

}
