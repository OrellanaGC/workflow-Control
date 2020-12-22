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
//import javax.validation.constraints.NotBlank;//

/**
 * @author aleja
 *
 */
@Entity
@Table(name = "tipo_de_evento")
public class TipoEvento {
	@Id
	@GeneratedValue
	private Long id;	
	private String nombre;
	private String icono;
	@OneToMany(mappedBy="tipoEvento")
    private Set<Evento> eventos;
	
	public TipoEvento() {		
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

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}	
	
}
