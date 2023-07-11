package com.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.Rol;
import com.services.UsuarioBean;

@Named("loginBean")
@ViewScoped
public class LoginBean implements Serializable { 

	@EJB
	private UsuarioBean user;
	
	@Inject
	private EnumJSF enums;
	
	@Inject
	private AuthJWTBean auth;
	
	private String nombreUsuario;
	private String contrasenia;
	private Rol rol;
	
	public LoginBean() {}

	public String login() {
		try {
			auth.borrar();
			if(rol == Rol.ANALISTA) {
				Analista usu = user.login(nombreUsuario, contrasenia, Analista.class);
				auth.generar(usu.getIdUsuario(), usu.getIdAnalista(), nombreUsuario, rol, usu);
			} else if(rol == Rol.ESTUDIANTE) {
				Estudiante usu = user.login(nombreUsuario, contrasenia, Estudiante.class);
				auth.generar(usu.getIdUsuario(), usu.getIdEstudiante(), nombreUsuario, rol, usu);
			} else if(rol == Rol.TUTOR) {
				Tutor usu = user.login(nombreUsuario, contrasenia, Tutor.class);
				auth.generar(usu.getIdUsuario(), usu.getIdTutor(), nombreUsuario, rol, usu);
			}

			addMessage(FacesMessage.SEVERITY_INFO, "Incio de sesion exitoso:", "El usuario " + nombreUsuario + " inicio sesion con exito");
			return "inicio.xhtml";
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error al inicar sesion:", e.getMessage());
			return "";
		}
	}
	
	public void info() {
		if(!auth.yaGenerado()) return;
		addMessage(FacesMessage.SEVERITY_INFO, "Informacion de Usuario:", auth.getRol().toString() + " " + auth.getNombreUsuario() + " " + auth.getIdUsuario() + " " + auth.getIdRol());
	}

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
