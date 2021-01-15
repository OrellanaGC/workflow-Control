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
import org.hibernate.annotations.SQLUpdate;

@Entity
@Table(name = "diagrama")
public class Diagrama {
	@Id
	@GeneratedValue
	private Long id;
	private String pathImagen;
	private String pathArchivo;
	@Column(unique = true)
	private String nombre;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="diagrama")
	@OrderBy("id ASC")
	private Set<Pool> pools;
	@ManyToOne
	@JoinColumn(name = "email_user", nullable = false)
	private User user;
	private Boolean confirmado;
		
	//Constructor
	public Diagrama() {		
	}
	//Getter y Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPathImagen() {
		return pathImagen;
	}
	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}
	public String getPathArchivo() {
		return pathArchivo;
	}
	public void setPathArchivo(String pathArchivo) {
		this.pathArchivo = pathArchivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<Pool> getPools() {
		return pools;
	}
	public void setPools(Set<Pool> pools) {
		this.pools = pools;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
	
	
}
