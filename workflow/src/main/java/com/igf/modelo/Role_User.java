package com.igf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity //Esto sirve para que JPA sepa que esta es una tabla de la base de datos
@Table(name = "role_user")
public class Role_User {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne	
	@JoinColumn(name ="user_id", nullable = false)	
	private User user;
	@ManyToOne	
	@JoinColumn(name ="role_id", nullable = false)	
	private Role role;
	public Role_User() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
