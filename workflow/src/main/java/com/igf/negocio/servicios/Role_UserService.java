package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igf.modelo.Role_User;
import com.igf.negocio.repositorio.Role_UserRepository;



@Service
public class Role_UserService {

	@Autowired
	private Role_UserRepository role_UserRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public Role_User save(Role_User role_User) {
		return this.role_UserRepository.save(role_User);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.role_UserRepository.deleteById(id);
	}
	
	/*
	 * Metodo para buscar Role_User por ID
	 */
	public Optional<Role_User> find(Long id) {
		return this.role_UserRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos los Role_User
	 */
	public List<Role_User> list(){
		return this.role_UserRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.role_UserRepository.existsById(id);
	}
}
