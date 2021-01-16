package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igf.modelo.DetalleVariable;
import com.igf.modelo.OpcionesVariable;
import com.igf.negocio.repositorio.OpcionesVariableRepository;

@Service
public class OpcionesVariableService {
	@Autowired
	private OpcionesVariableRepository opcionesVariableRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public OpcionesVariable save(OpcionesVariable opcionesVariable) {
		return this.opcionesVariableRepository.save(opcionesVariable);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.opcionesVariableRepository.deleteById(id);
	}

	/*
	 * Metodo para buscar Tarea por ID
	 */
	public Optional<OpcionesVariable> find(Long id) {
		return this.opcionesVariableRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos las Tareas
	 */
	public List<OpcionesVariable> list(){
		return this.opcionesVariableRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.opcionesVariableRepository.existsById(id);		
	}
}
