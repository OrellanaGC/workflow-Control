package com.igf.negocio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igf.modelo.Pool;

public interface PoolRepository extends JpaRepository<Pool, Long>{	
	public List<Pool> findByNombre(String nombre);		

}
