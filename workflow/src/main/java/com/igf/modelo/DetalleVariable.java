package com.igf.modelo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class DetalleVariable {
	@Id
	@GeneratedValue
	private Long id;
	private String tipoVariable;
	private String nombreVariable;
	private boolean requerido;
	private Integer maximo;
	private Integer minimo;
	private Integer maxCaracter;
	private Integer minCaracter;
	private Integer step;
	@ManyToOne
	@JoinColumn(name = "id_tarea")
	private Tarea tarea;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "detalleVariable")
	@OrderBy("id ASC")
	private Set<OpcionesVariable> opcionesVariables;
	
	
	//Constructor
	public DetalleVariable() {
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

	public String getTipoVariable() {
		return tipoVariable;
	}

	public void setTipoVariable(String tipoVariable) {
		this.tipoVariable = tipoVariable;
	}

	public boolean isRequerido() {
		return requerido;
	}

	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

	public Integer getMaximo() {
		return maximo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public Integer getMinimo() {
		return minimo;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public Integer getMaxCaracter() {
		return maxCaracter;
	}

	public void setMaxCaracter(Integer maxCaracter) {
		this.maxCaracter = maxCaracter;
	}

	public Integer getMinCaracter() {
		return minCaracter;
	}

	public void setMinCaracter(Integer minCaracter) {
		this.minCaracter = minCaracter;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Set<OpcionesVariable> getOpcionesVariables() {
		return opcionesVariables;
	}

	public void setOpcionesVariables(Set<OpcionesVariable> opcionesVariables) {
		this.opcionesVariables = opcionesVariables;
	}

	public String getNombreVariable() {
		return nombreVariable;
	}

	public void setNombreVariable(String nombreVariable) {
		this.nombreVariable = nombreVariable;
	}
	

}
