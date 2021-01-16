package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igf.modelo.DetalleVariable;
import com.igf.modelo.Tarea;
import com.igf.negocio.repositorio.DetalleVariableRepository;

@Service
public class DetalleVariableService {
	@Autowired
	private DetalleVariableRepository detalleVariableRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public DetalleVariable save(DetalleVariable detalleVariable) {
		return this.detalleVariableRepository.save(detalleVariable);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.detalleVariableRepository.deleteById(id);
	}

	/*
	 * Metodo para buscar Tarea por ID
	 */
	public Optional<DetalleVariable> find(Long id) {
		return this.detalleVariableRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos las Tareas
	 */
	public List<DetalleVariable> list(){
		return this.detalleVariableRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.detalleVariableRepository.existsById(id);
	}
}
