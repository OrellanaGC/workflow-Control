package com.igf.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="pool")
	@OrderBy("id ASC")
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
