package com.igf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OpcionesVariable {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "id_detalle_variable")
	private DetalleVariable detalleVariable;
	
	//Constructor
	public OpcionesVariable() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Getter y setter
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

	public DetalleVariable getDetalleVariable() {
		return detalleVariable;
	}

	public void setDetalleVariable(DetalleVariable detalleVariable) {
		this.detalleVariable = detalleVariable;
	}


}
