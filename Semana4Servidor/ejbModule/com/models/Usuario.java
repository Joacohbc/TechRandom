package com.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name = "USUARIOS")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private long idUsuario;

	private String apellido;

	private String clave;

	private String documento;

	private String mail;

	private String nombre;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	private Rol role;

	public Usuario() {
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol getRole() {
		return this.role;
	}

	public void setRole(Rol role) {
		this.role = role;
	}

}