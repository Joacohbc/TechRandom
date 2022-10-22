package com.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.entities.enums.Departamento;
import com.entities.enums.EstadoUsuario;
import com.entities.enums.Genero;

/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name = "USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private Long idUsuario;

	@Column(nullable = false)
	private String apellidos;

	@Column(nullable = false)
	private String contrasena;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Departamento departamento;

	@Column(nullable = false)
	private String documento;

	@Column(nullable = false, unique = true)
	private String email;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private EstadoUsuario estadoUsuario;

	@Column(nullable = false)
	private Date fecNacimiento;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Genero genero;

	@Column(nullable = false)
	private String localidad;

	@Column(unique = true, nullable = false)
	private String nombreUsuario;

	@Column(nullable = false)
	private String nombres;

	@Column(nullable = false)
	private String telefono;

	// bi-directional many-to-one association to Itr
	@ManyToOne
	@JoinColumn(name = "ID_ITR")
	private Itr itr;

	public Usuario() {
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EstadoUsuario getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(EstadoUsuario estado) {
		this.estadoUsuario = estado;
	}

	public Date getFecNacimiento() {
		return this.fecNacimiento;
	}

	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Itr getItr() {
		return this.itr;
	}

	public void setItr(Itr itr) {
		this.itr = itr;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", apellidos=" + apellidos + ", contrasena=" + contrasena
				+ ", departamento=" + departamento + ", documento=" + documento + ", email=" + email
				+ ", estadoUsuario=" + estadoUsuario + ", fecNacimiento=" + fecNacimiento + ", genero=" + genero
				+ ", localidad=" + localidad + ", nombreUsuario=" + nombreUsuario + ", nombres=" + nombres
				+ ", telefono=" + telefono + "]";
	}

}