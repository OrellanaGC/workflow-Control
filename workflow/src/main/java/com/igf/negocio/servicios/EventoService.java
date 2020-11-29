package com.igf.negocio.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igf.modelo.Evento;
import com.igf.negocio.repositorio.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	/*
	 * Metodo para guardar o actualizar 
	 */
	public Evento save(Evento evento) {
		return this.eventoRepository.save(evento);
	}
	
	/*
	 * Metodo para eliminar 
	 */
	public void delete(Long id) {
		this.eventoRepository.deleteById(id);
	}
	
	/*
	 * Metodo para buscar Evento por id
	 */
	public Optional<Evento> find(Long id) {
		return this.eventoRepository.findById(id);		
	}
	
	/*
	 * Metodo para listar todos los Eventos
	 */
	public List<Evento> list(){
		return this.eventoRepository.findAll();		
	}
	
	/*
	 * Metodo para saber si el registro existe en la base de datos
	 */
	public boolean exists(Long id) {
		return this.eventoRepository.existsById(id);
	}
}
