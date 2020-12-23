/**
 * 
 */
package com.igf.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * @author Pc
 *
 */
public class Permission {
@Id 
@GeneratedValue
private Long id;
@NotBlank(message = " el nombre no debe estar vacio")
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
