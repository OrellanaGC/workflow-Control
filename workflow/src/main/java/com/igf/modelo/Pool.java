package com.igf.modelo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pool")
public class Pool {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String nombre;
	@ManyToOne
	@JoinColumn(name ="id_diagrama", nullable = false)
	private Diagrama diagrama;
	@OneToMany(mappedBy = "pool")
	private Set<Tarea> tareas;
	
	public Pool() {
		
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
	public Diagrama getDiagrama() {
		return diagrama;
	}
	public void setDiagrama(Diagrama diagrama) {
		this.diagrama = diagrama;
	}
	public Set<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
}
