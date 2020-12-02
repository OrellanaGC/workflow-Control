package com.igf.negocio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igf.modelo.Evento;
import com.igf.modelo.TipoEvento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
  
}

