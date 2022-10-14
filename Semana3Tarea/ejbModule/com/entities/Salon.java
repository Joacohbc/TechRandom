package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Salon
 *
 */
@Entity
public class Salon implements Serializable {

	private static final long serialVersionUID = 1L;

	public Salon() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer capMax;

	private Boolean practicas;

	@Enumerated
	private TipoSalon tipo;

	@ManyToOne
	private Area area;

	@OneToMany
	private List<Material> materiales = new ArrayList<Material>();

	public List<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCapMax() {
		return capMax;
	}

	public void setCapMax(Integer capMax) {
		this.capMax = capMax;
	}

	public Boolean getPracticas() {
		return practicas;
	}

	public void setPracticas(Boolean practicas) {
		this.practicas = practicas;
	}

	public TipoSalon getTipo() {
		return tipo;
	}

	public void setTipo(TipoSalon tipo) {
		this.tipo = tipo;
	}

}
