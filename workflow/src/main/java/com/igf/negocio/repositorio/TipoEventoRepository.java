package com.igf.negocio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igf.modelo.TipoEvento;


public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long>{
	public List<TipoEvento> findByNombre(String nombre);		
}
