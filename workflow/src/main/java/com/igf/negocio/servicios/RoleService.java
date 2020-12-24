/**
 * 
 */
package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.igf.modelo.Role;

import com.igf.negocio.repositorio.RoleRepository;

/**
 * @author Pc
 *
 */
@Service
public class RoleService {
@Autowired
private RoleRepository roleRepository;

/*
 * Metodo para guardar o actualizar 
 */
public Role save(Role role) {
	return this.roleRepository.save(role);
}

/*
 * Metodo para eliminar 
 */
public void delete(Long id) {
	this.roleRepository.deleteById(id);
}

/*
 * Metodo para buscar Evento por ID
 */
public Optional<Role> find(Long id) {
	return this.roleRepository.findById(id);		
}

/*
 * Metodo para listar todos los Eventos
 */
public List<Role> list(){
	return this.roleRepository.findAll();		
}

/*
 * Metodo para saber si el registro existe en la base de datos
 */
public boolean exists(Long id) {
	return this.roleRepository.existsById(id);
}

}
