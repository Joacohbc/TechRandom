package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ACCION_CONSTANCIAS database table.
 * 
 */
@Entity
@Table(name="ACCION_CONSTANCIAS")
@NamedQuery(name="AccionConstancia.findAll", query="SELECT a FROM AccionConstancia a")
public class AccionConstancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_ACCION_CONSTANCIA")
	private Long idAccionConstancia;

	private String detalle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_HORA")
	private Date fechaHora;

	//bi-directional many-to-one association to Analista
	@ManyToOne
	@JoinColumn(name="ID_ANALISTA")
	private Analista analista;

	//bi-directional many-to-one association to Constancia
	@ManyToOne
	@JoinColumn(name="ID_CONSTANCIA")
	private Constancia constancia;

	public AccionConstancia() {
	}

	public Long getIdAccionConstancia() {
		return this.idAccionConstancia;
	}

	public void setIdAccionConstancia(Long idAccionConstancia) {
		this.idAccionConstancia = idAccionConstancia;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Analista getAnalista() {
		return this.analista;
	}

	public void setAnalista(Analista analista) {
		this.analista = analista;
	}

	public Constancia getConstancia() {
		return this.constancia;
	}

	public void setConstancia(Constancia constancia) {
		this.constancia = constancia;
	}

}