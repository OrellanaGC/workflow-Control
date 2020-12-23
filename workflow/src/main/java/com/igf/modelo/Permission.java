<<<<<<< HEAD
/**
 * 
 */
package com.igf.modelo;

//import java.util.Set;//

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

/**
 * @author Pc
 *
 */
@Entity
=======
package com.igf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity //Esto sirve para que JPA sepa que esta es una tabla de la base de datos
>>>>>>> Eqiuipo1errorYvalidaciones
@Table(name = "permission")
public class Permission {
	@Id
	@GeneratedValue
	private Long id;
<<<<<<< HEAD
	private String nombre;
	
	@ManyToOne
	private Permission_role permission_role;
	
	
	public Permission()
	{
		
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



=======
	@NotBlank(message = "El nombre no debe estar vacio")
	private String nombre;	
	
	public Permission() {
		
	}

>>>>>>> Eqiuipo1errorYvalidaciones
	public Long getId() {
		return id;
	}

<<<<<<< HEAD


=======
>>>>>>> Eqiuipo1errorYvalidaciones
	public void setId(Long id) {
		this.id = id;
	}

<<<<<<< HEAD


	public Permission_role getPermission_role() {
		return permission_role;
	}



	public void setPermission_role(Permission_role permission_role) {
		this.permission_role = permission_role;
	}

=======
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
>>>>>>> Eqiuipo1errorYvalidaciones
	
}
