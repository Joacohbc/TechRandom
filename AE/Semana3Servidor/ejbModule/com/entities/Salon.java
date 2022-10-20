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

	@Column(length = 40, unique = true)
	private String nombre;
	
	private Integer capMax;

	private Boolean practicas;

	@Enumerated
	private TipoSalon tipo;

	@ManyToOne
	private Area area;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Material> materiales = new ArrayList<Material>();


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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
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
	
	public List<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}

	@Override
	public String toString() {
		return "Salon [id=" + id + ", capMax=" + capMax + ", practicas=" + practicas + ", tipo=" + tipo + ", area="
				+ area + "]";
	}

}
