package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.igf.modelo.Diagrama;
import com.igf.negocio.repositorio.DiagramaRepository;

@Service
public class DiagramaService {
	
	@Autowired
	private DiagramaRepository diagramaRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public Diagrama save(Diagrama diagrama) {
		return this.diagramaRepository.save(diagrama);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.diagramaRepository.deleteById(id);
	}
	
	/*
	 * Metodo para buscar Diagrama por ID
	 */
	public Optional<Diagrama> find(Long id) {
		return this.diagramaRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos los Diagramas
	 */
	public List<Diagrama> list(){
		return this.diagramaRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.diagramaRepository.existsById(id);		
	}
	
	/*
	 * Metodo para listar encontrar un diagrama por su nombre
	 */
	public List<Diagrama> findBynombre(String nombre){
		return this.diagramaRepository.findByNombre(nombre);
	}
}
