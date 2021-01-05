package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.igf.modelo.Pool;
import com.igf.negocio.repositorio.PoolRepository;

@Service
public class PoolService {
	
	@Autowired
	private PoolRepository poolRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public Pool save(Pool pool) {
		return this.poolRepository.save(pool);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.poolRepository.deleteById(id);
	}
	
	/*
	 * Metodo para buscar Pool por ID
	 */
	public Optional<Pool> find(Long id) {
		return this.poolRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos los Pools
	 */
	public List<Pool> list(){
		return this.poolRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.poolRepository.existsById(id);		
	}
	
	/*
	 * Metodo para listar encontrar un pool por su nombre
	 */
	public List<Pool> findBynombre(String nombre){
		return this.poolRepository.findByNombre(nombre);
	}
}