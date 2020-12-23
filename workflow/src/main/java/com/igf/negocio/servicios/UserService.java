package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.igf.modelo.User;
import com.igf.negocio.repositorio.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public User save(User user) {
		return this.userRepository.save(user);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(String email) {
		this.userRepository.deleteById(email);
	}
	
	/*
	 * Metodo para buscar Evento por ID
	 */
	public Optional<User> find(String email) {
		return this.userRepository.findById(email);		
	}
	
	/*
	 * Metodo para listar todos los Eventos
	 */
	public List<User> list(){
		return this.userRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(String email) {
		return this.userRepository.existsById(email);
	}
}
