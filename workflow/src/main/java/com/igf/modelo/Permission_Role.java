package com.igf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "permission_role")
public class Permission_Role {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne	
	@JoinColumn(name ="id_permission", nullable = false)	
	private Permission permission;
	@ManyToOne	
	@JoinColumn(name ="id_role", nullable = false)	
	private Role role;
	public Permission_Role() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
