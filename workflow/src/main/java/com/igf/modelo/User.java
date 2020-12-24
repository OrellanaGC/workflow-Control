package com.igf.modelo;



import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;



@Entity 
@Table(name = "users")

public class User {
	
	@Id
	@NotBlank(message = "debe escribir un email")
	private String email;
	@NotBlank(message = "El nombre no debe estar vacio")
	private String name;
	@NotBlank(message = "La contraseña no debe estar vacio")
	private String password;
	@NotBlank(message = "No deje el campo vacio")
	private String sexo;
	@NotBlank(message = "Ingrese la fecha")
	private Date fecha_nac;
	@OneToMany(mappedBy = "user")
	private Set<Diagrama> diagramas;
	
	public User() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public Set<Diagrama> getDiagramas() {
		return diagramas;
	}

	public void setDiagramas(Set<Diagrama> diagramas) {
		this.diagramas = diagramas;
	}
}
