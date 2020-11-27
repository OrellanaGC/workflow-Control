package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igf.modelo.TipoEvento;
import com.igf.negocio.repositorio.TipoEventoRepository;

@Service
public class TipoEventoService {
	
	@Autowired
	private TipoEventoRepository tipoEventoRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public TipoEvento save(TipoEvento tipoEvento) {
		return this.tipoEventoRepository.save(tipoEvento);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.tipoEventoRepository.deleteById(id);
	}
	
	/*
	 * Metodo para buscar tipoEvento por id
	 */
	public Optional<TipoEvento> find(Long id) {
		return this.tipoEventoRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos los tiposEventos
	 */
	public List<TipoEvento> list(){
		return this.tipoEventoRepository.findAll();
	}
	
}
