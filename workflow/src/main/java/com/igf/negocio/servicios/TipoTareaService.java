/**
 * 
 */
package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.igf.modelo.TipoTarea;
import com.igf.negocio.repositorio.TipoTareaRepository;

/**
 * @author daiv05
 *
 */

public class TipoTareaService {
	
	@Autowired
	private TipoTareaRepository tipoTareaRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public TipoTarea save(TipoTarea tipoTarea) {
		return this.tipoTareaRepository.save(tipoTarea);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.tipoTareaRepository.deleteById(id);
	}
	
	/*
	 * Metodo para buscar tipoTarea por id
	 */
	public Optional<TipoTarea> find(Long id) {
		return this.tipoTareaRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos los tiposTareas
	 */
	public List<TipoTarea> list(){
		return this.tipoTareaRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.tipoTareaRepository.existsById(id);
	}
}
