package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Area
 *
 */
@Entity
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;

	public Area() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 40, unique = true)
	private String nombre;

	@OneToMany(mappedBy = "area")
	private List<Salon> salones = new ArrayList<Salon>();

	// Getters y Setters
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

	public List<Salon> getSalones() {
		return salones;
	}

	public void setSalones(List<Salon> salones) {
		this.salones = salones;
	}
}
