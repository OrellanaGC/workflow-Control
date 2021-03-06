package com.igf.modelo;


import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sun.istack.Nullable;

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
	private boolean cambios;
	@ManyToOne
	@JoinColumn(name = "id_pool", nullable = false)
	private Pool pool;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "tarea")
	@OrderBy("id ASC")
	private Set<DetalleVariable> detalleVariables;
	
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

	public Pool getPool() {
		return pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}

	public Set<DetalleVariable> getDetalleVariables() {
		return detalleVariables;
	}

	public void setDetalleVariables(Set<DetalleVariable> detalleVariables) {
		this.detalleVariables = detalleVariables;
	}

	public boolean isCambios() {
		return cambios;
	}

	public void setCambios(boolean cambios) {
		this.cambios = cambios;
	}
	
	
}
