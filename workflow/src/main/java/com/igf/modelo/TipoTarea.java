/**
 * 
 */
package com.igf.modelo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author daiv05
 *
 */
@Entity
@Table(name = "tipo_de_tarea")
public class TipoTarea {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	@OneToMany(mappedBy = "tipoTarea")
	private Set<Tarea> tareas;
	
	public TipoTarea() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}
	
}
