package com.igf.negocio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igf.modelo.Diagrama;
import com.igf.modelo.TipoEvento;

public interface DiagramaRepository extends JpaRepository<Diagrama, Long>{	
	public List<Diagrama> findByNombre(String nombre);		
}
