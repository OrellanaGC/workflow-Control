/**
 * 
 */
package com.igf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author aleja
 *
 */
@Entity //Esto sirve para que JPA sepa que esta es una tabla de la base de datos
@Table(name = "evento")
public class Evento {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "El nombre no debe estar vacio.")
	private String nombre;
	@ManyToOne	
	@JoinColumn(name ="id_tipo_evento", nullable = false)	
	@javax.validation.constraints.NotNull(message = "Se debe seleccionar un tipo de evento")
	private TipoEvento tipoEvento;
	public Evento() {
		
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
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
}
