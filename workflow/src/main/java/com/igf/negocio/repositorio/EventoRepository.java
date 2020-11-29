package com.igf.negocio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igf.modelo.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{

}
