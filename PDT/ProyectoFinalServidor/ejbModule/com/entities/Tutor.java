package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.entities.enums.TipoTutor;

import java.util.List;

/**
 * The persistent class for the TUTORES database table.
 * 
 */
@Entity
@Table(name = "TUTORES")
public class Tutor extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String area; // TODO: Esto deberia ser un ENUM o una Tabla aparte?

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoTutor tipo;
	
	@Column(nullable = false) 
	private Boolean estado;

	@OneToMany(mappedBy = "tutor")
	private List<Responsable> responsables;

	public Tutor() {
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public TipoTutor getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoTutor tipo) {
		this.tipo = tipo;
	}

	public List<Responsable> getResponsables() {
		return this.responsables;
	}

	public void setResponsables(List<Responsable> responsables) {
		this.responsables = responsables;
	}

	public Responsable addResponsable(Responsable responsable) {
		getResponsables().add(responsable);
		responsable.setTutore(this);

		return responsable;
	}

	public Responsable removeResponsable(Responsable responsable) {
		getResponsables().remove(responsable);
		responsable.setTutore(null);

		return responsable;
	}
}