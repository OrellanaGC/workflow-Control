/**
 * 
 */
package com.igf.negocio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igf.modelo.Tarea;

/**
 * @author daiv05
 *
 */
public interface TareaRepository extends JpaRepository<Tarea, Long>{

}
