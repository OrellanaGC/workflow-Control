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
@Table(name = "permission")
public class Permission {
	@Id
	@GeneratedValue
	private Long id;
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



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Permission_role getPermission_role() {
		return permission_role;
	}



	public void setPermission_role(Permission_role permission_role) {
		this.permission_role = permission_role;
	}

	
}
