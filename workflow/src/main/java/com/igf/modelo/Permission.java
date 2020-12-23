package com.igf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity //Esto sirve para que JPA sepa que esta es una tabla de la base de datos
@Table(name = "permission")
public class Permission {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "El nombre no debe estar vacio")
	private String nombre;	
	
	public Permission() {
		
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
	
}
