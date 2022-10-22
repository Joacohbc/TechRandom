package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the EVENTOS database table.
 * 
 */
@Entity
@Table(name="EVENTOS")
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_EVENTO")
	private Long idEvento;

	private Boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;

	private String titulo;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="evento")
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy="evento")
	private List<Constancia> constancias;

	//bi-directional many-to-one association to TipoEvento
	@ManyToOne
	@JoinColumn(name="ID_TIPO_EVENTO")
	private TipoEvento tipoEvento;

	//bi-directional many-to-one association to Gestore
	@OneToMany(mappedBy="evento")
	private List<Gestor> gestores;

	//bi-directional many-to-one association to Justificacione
	@OneToMany(mappedBy="evento")
	private List<Justificacion> justificaciones;

	//bi-directional many-to-one association to Reclamo
	@OneToMany(mappedBy="evento")
	private List<Reclamo> reclamos;

	//bi-directional many-to-one association to Responsable
	@OneToMany(mappedBy="evento")
	private List<Responsable> responsables;

	public Evento() {
	}

	public Long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setEvento(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setEvento(null);

		return asistencia;
	}

	public List<Constancia> getConstancias() {
		return this.constancias;
	}

	public void setConstancias(List<Constancia> constancias) {
		this.constancias = constancias;
	}

	public Constancia addConstancia(Constancia constancia) {
		getConstancias().add(constancia);
		constancia.setEvento(this);

		return constancia;
	}

	public Constancia removeConstancia(Constancia constancia) {
		getConstancias().remove(constancia);
		constancia.setEvento(null);

		return constancia;
	}

	public TipoEvento getTipoEvento() {
		return this.tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public List<Gestor> getGestores() {
		return this.gestores;
	}

	public void setGestores(List<Gestor> gestores) {
		this.gestores = gestores;
	}

	public Gestor addGestore(Gestor gestore) {
		getGestores().add(gestore);
		gestore.setEvento(this);

		return gestore;
	}

	public Gestor removeGestore(Gestor gestore) {
		getGestores().remove(gestore);
		gestore.setEvento(null);

		return gestore;
	}

	public List<Justificacion> getJustificaciones() {
		return this.justificaciones;
	}

	public void setJustificaciones(List<Justificacion> justificaciones) {
		this.justificaciones = justificaciones;
	}

	public Justificacion addJustificacione(Justificacion justificacione) {
		getJustificaciones().add(justificacione);
		justificacione.setEvento(this);

		return justificacione;
	}

	public Justificacion removeJustificacione(Justificacion justificacione) {
		getJustificaciones().remove(justificacione);
		justificacione.setEvento(null);

		return justificacione;
	}

	public List<Reclamo> getReclamos() {
		return this.reclamos;
	}

	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}

	public Reclamo addReclamo(Reclamo reclamo) {
		getReclamos().add(reclamo);
		reclamo.setEvento(this);

		return reclamo;
	}

	public Reclamo removeReclamo(Reclamo reclamo) {
		getReclamos().remove(reclamo);
		reclamo.setEvento(null);

		return reclamo;
	}

	public List<Responsable> getResponsables() {
		return this.responsables;
	}

	public void setResponsables(List<Responsable> responsables) {
		this.responsables = responsables;
	}

	public Responsable addResponsable(Responsable responsable) {
		getResponsables().add(responsable);
		responsable.setEvento(this);

		return responsable;
	}

	public Responsable removeResponsable(Responsable responsable) {
		getResponsables().remove(responsable);
		responsable.setEvento(null);

		return responsable;
	}

}