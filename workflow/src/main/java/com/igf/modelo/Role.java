package com.igf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity 
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "El nombre no debe estar vacio")
	private String nombre;	
	public Role() {}
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
	
}
