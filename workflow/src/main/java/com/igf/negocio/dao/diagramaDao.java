package com.igf.negocio.dao;

public class diagramaDao {
	private String nombre;	
	private String LineaPadre;
	private Long id;
	private String descripcion;
	
	//Constructor
	public diagramaDao() {				
	}
	
	//Getter y Setter
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	public String getLineaPadre() {
		return LineaPadre;
	}
	public void setLineaPadre(String lineaPadre) {
		LineaPadre = lineaPadre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
