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
 * @author Pc
 *
 */
@Entity
@Table(name = "permission_role")
public class Permission_role {
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(mappedBy ="permission_role")
	private Set<Permission_role>permission;
	

	public Permission_role(Long id) {
		
		this.id = id;
	}

	

	public Set<Permission_role> getPermission() {
		return permission;
	}

	public void setPermission(Set<Permission_role> permission) {
		this.permission = permission;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	
}
