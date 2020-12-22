/**
 * 
 */
package com.igf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author daiv05
 *
 */
@Entity
@Table(name = "tarea")
public class Tarea {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "id_tipo_tarea", nullable = false)
	private TipoTarea tipoTarea;
	
	public Tarea() {
		
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoTarea getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(TipoTarea tipoTarea) {
		this.tipoTarea = tipoTarea;
	}
}
