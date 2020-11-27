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

/**
 * @author aleja
 *
 */
@Entity
@Table(name = "evento")
public class Evento {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	@ManyToOne
	@JoinColumn(name ="id_tipo_evento", nullable = false)
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