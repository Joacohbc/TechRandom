package com.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.InvalidEntityException;
import com.services.UsuarioBean;
import com.validation.ValidacionesUsuario.TipoUsuarioDocumento;

@Named("usuario")
@SessionScoped
public class UsuarioJSF implements Serializable {
	
	@EJB
	private UsuarioBean bean;
	
	@Inject
	private EnumJSF enumJSF;
	
	// Datos del usuario
	private Usuario usuario;
	
	// Datos del Departamento y Genero
	private String departamento;
	private String genero;
	
	// Datos del Tutor
	private String area;
	private String tipoTutor;
	
	// Datos del estudiante
	private Integer generacion;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}
	
	public void crearAnalista() {
		Analista a = new Analista();
		a.setDocumento(usuario.getDocumento());
		a.setNombres(usuario.getNombres());
		a.setApellidos(usuario.getApellidos());
		a.setEmailPersonal(usuario.getEmailPersonal());
		a.setEmailUtec(usuario.getEmailUtec());
		a.setTelefono(usuario.getTelefono());
		a.setFecNacimiento(usuario.getFecNacimiento());
		a.setLocalidad(usuario.getLocalidad());
		a.setContrasena(usuario.getContrasena());
		a.setDepartamento(enumJSF.toDepartamento(departamento));
		a.setGenero(enumJSF.toGenero(genero));
		
		// Harcodeado
		a.setEstado(true);
		a.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
		
		Itr itr = new Itr();
		itr.setIdItr(1l);
		a.setItr(itr);
		
		try {
			bean.register(a, TipoUsuarioDocumento.URUGUAYO);
			addMessage(FacesMessage.SEVERITY_INFO, "Usuario creado con exito:", String.format("El Analista %s %s fue creado con exito", a.getNombres(), a.getApellidos()));
		} catch (InvalidEntityException e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error de ingreso:", e.getMessage());
		}
	}
	
	public void crearEstudiante() {
		Estudiante e = new Estudiante();
		e.setDocumento(usuario.getDocumento());
		e.setNombres(usuario.getNombres());
		e.setApellidos(usuario.getApellidos());
		e.setEmailPersonal(usuario.getEmailPersonal());
		e.setEmailUtec(usuario.getEmailUtec());
		e.setTelefono(usuario.getTelefono());
		e.setFecNacimiento(usuario.getFecNacimiento());
		e.setLocalidad(usuario.getLocalidad());
		e.setContrasena(usuario.getContrasena());
		e.setDepartamento(enumJSF.toDepartamento(departamento));
		e.setGenero(enumJSF.toGenero(genero));
		
		// Datos de Estudiante
		e.setGeneracion(generacion);
		
		// Harcodeado
		e.setEstado(true);
		e.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
		
		Itr itr = new Itr();
		itr.setIdItr(1l);
		e.setItr(itr);
		
		try {
			bean.register(e, TipoUsuarioDocumento.URUGUAYO);
			addMessage(FacesMessage.SEVERITY_INFO, "Usuario creado con exito:", String.format("El Estudiante %s %s fue creado con exito", e.getNombres(), e.getApellidos()));
		} catch (InvalidEntityException ex) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error de ingreso:", ex.getMessage());
		}
	}
	
	public void crearTutor() {
		Tutor t = new Tutor();
		t.setDocumento(usuario.getDocumento());
		t.setNombres(usuario.getNombres());
		t.setApellidos(usuario.getApellidos());
		t.setEmailPersonal(usuario.getEmailPersonal());
		t.setEmailUtec(usuario.getEmailUtec());
		t.setTelefono(usuario.getTelefono());
		t.setFecNacimiento(usuario.getFecNacimiento());
		t.setLocalidad(usuario.getLocalidad());
		t.setContrasena(usuario.getContrasena());
		t.setDepartamento(enumJSF.toDepartamento(departamento));
		t.setGenero(enumJSF.toGenero(genero));
		
		// Datos de tutor
		t.setArea(area);
		t.setTipo(enumJSF.toTipoTutor(tipoTutor));
		
		// Harcodeado
		t.setEstado(true);
		t.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
		
		Itr itr = new Itr();
		itr.setIdItr(1l);
		t.setItr(itr);
		
		try {
			bean.register(t, TipoUsuarioDocumento.URUGUAYO);
			addMessage(FacesMessage.SEVERITY_INFO, "Usuario creado con exito:", String.format("El Tutor %s %s fue creado con exito", t.getNombres(), t.getApellidos()));
		} catch (InvalidEntityException e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error de ingreso:", e.getMessage());
		}
	}
	
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public Usuario getUsuario() {
    	return usuario;
    }

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTipoTutor() {
		return tipoTutor;
	}

	public void setTipoTutor(String tipoTutor) {
		this.tipoTutor = tipoTutor;
	}

	public Integer getGeneracion() {
		return generacion;
	}

	public void setGeneracion(Integer generacion) {
		this.generacion = generacion;
	}
}
