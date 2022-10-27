package com.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Table(name = "ROLES")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROL")	
	@SequenceGenerator(name = "SEQ_ROL", initialValue = 1, allocationSize = 1)
	@Column(name = "ID_ROL")
	private long idRol;

	@Column(unique = true)
	private String nombre;
	
	private String descripcion;
	
	@ManyToMany
	@JoinTable(name = "ROL_FUNCION",
		joinColumns = { @JoinColumn(name = "ID_ROL") }, 
		inverseJoinColumns = { @JoinColumn(name = "ID_FUNCION") })
	private List<Funcionalidad> funcionalidades;

	@OneToMany(mappedBy = "role")
	private List<Usuario> usuarios;

	public Rol() {
	}

	public long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return this.funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRole(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRole(null);

		return usuario;
	}

}