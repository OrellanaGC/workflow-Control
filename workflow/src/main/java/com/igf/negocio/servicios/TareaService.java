/**
 * 
 */
package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igf.modelo.Tarea;
import com.igf.negocio.repositorio.TareaRepository;

/**
 * @author daiv05
 *
 */

@Service
public class TareaService {
	
	@Autowired
	private TareaRepository tareaRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public Tarea save(Tarea tarea) {
		return this.tareaRepository.save(tarea);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.tareaRepository.deleteById(id);
	}
	
	/*
	 * Metodo para buscar Tarea por ID
	 */
	public Optional<Tarea> find(Long id) {
		return this.tareaRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos las Tareas
	 */
	public List<Tarea> list(){
		return this.tareaRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.tareaRepository.existsById(id);
	}

}
