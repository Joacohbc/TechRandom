package com.beans;

import java.io.Serializable;
import java.util.List;

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
import com.entities.enums.Rol;
import com.exceptions.InvalidEntityException;
import com.services.UsuarioBean;
import com.validation.ValidacionesUsuario.TipoUsuarioDocumento;

@Named("modificarUsuario")
@SessionScoped
public class ModificarUsuarioJSF implements Serializable {
	
	@EJB
	private UsuarioBean bean;
	
	@Inject
	private EnumJSF enumBean;
	
	private String telefono;
	
	private String rol;
	
	private Long idModificar;
	
	public void modificarusuario() {

	try {
		Rol r = enumBean.toRol(rol);
		
		if(r == Rol.ANALISTA) {
			Analista a = bean.findById(Analista.class, idModificar);
			a.setIdUsuario(idModificar);
			a.setTelefono(telefono);
			bean.updateAnalista(a);
		} else if(r == Rol.ESTUDIANTE) {
			Estudiante a = bean.findById(Estudiante.class, idModificar);
			a.setIdUsuario(idModificar);
			a.setTelefono(telefono);
			bean.updateEstudiante(a);
		} else {
			Tutor a = bean.findById(Tutor.class, idModificar);
			a.setIdUsuario(idModificar);
			a.setTelefono(telefono);
			bean.updateTutor(a);
		}
		
		addMessage(FacesMessage.SEVERITY_INFO, "Usuario actualizado", "Usuario actualizado");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error de ingreso:", e.getMessage());
		}
	}
	
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public UsuarioBean getBean() {
		return bean;
	}

	public void setBean(UsuarioBean bean) {
		this.bean = bean;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Long getIdModificar() {
		return idModificar;
	}

	public void setIdModificar(Long idModificar) {
		this.idModificar = idModificar;
	}
}
