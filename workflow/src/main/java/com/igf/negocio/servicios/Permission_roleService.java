/**
 * 
 */
package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igf.modelo.Permission_Role;

import com.igf.negocio.repositorio.Permission_RoleRepository;

/**
 * @author Pc
 *
 */
@Service
public class Permission_roleService {

	
private Permission_RoleRepository permission_RoleRepository;
@Autowired
	/*
	 * Metodo para guardar o actualizar 
	 */
	public Permission_Role save(Permission_Role permission_role) {
		return this.permission_RoleRepository.save(permission_role);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.permission_RoleRepository.deleteById(id);
	}
	
	/*
	 * Metodo para buscar Evento por ID
	 */
	public Optional<Permission_Role> find(Long id) {
		return this.permission_RoleRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos los Eventos
	 */
	public List<Permission_Role> list(){
		return this.permission_RoleRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.permission_RoleRepository.existsById(id);
	}
}
